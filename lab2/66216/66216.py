from bs4 import BeautifulSoup
import requests
import sys
import json
import codecs
import os
import urllib
import re

#Skrypt wywolujemy np. python 66216.py wynajem dom warszawa
#Jako wynik uzyskujemy w katalogu, w którym znajduje się skrypt podkatalogi z numerem oferty, w których
#znajudją się pliki json z opisem oferty oraz zdjęcia
#Scraper pobiera wszystkie oferty - dane są zapisane w obiektach Offer, na których podstawie można wykonać
#filtrację zwracanych wyników np. w funkcji __main__

class Offer:
	def __init__(self,title,details,description,price,features):
		self.title = title
		self.details = details
		self.description = description
		self.price = price
		self.link = None
		self.features = features
		self.offer_id = None

	def __init__(self, link, offer_id):
		self.link = link
		self.offer_id = offer_id
		print(self.link)
		self.set_values()

	def set_values(self):
		req = requests.get(self.link)
		soup = BeautifulSoup(req.text,'html.parser')
		self.title = soup.find('h1').text
		self.details = []
		self.description = ""
		self.features = []
		det = soup.find('section','section-overview').find_all('li')
		desc = soup.find('section','section-description').find_all('p')
		feats = soup.find('section','section-features')
		if(feats is not None):
			feat = feats.find_all('li')
			for f in feat:
				self.features.append(f.text)
		for d in det:
			self.details.append(d.text)
		for de in desc:
			self.description+=(de.text+'\n')
		self.price = soup.find('div','css-1vr19r7').text
		self.get_json()
		self.get_photos()

	def get_values(self):
		print("TITLE-------------------------------------------------------------------")
		print(self.title)
		print("PRICE-------------------------------------------------------------------")
		print(self.price)
		print("DESCRIPTION-------------------------------------------------------------")
		print(self.description)
		print("FEATURES----------------------------------------------------------------")
		print(self.features)
		print("DETAILS-----------------------------------------------------------------")
		print(self.details)

	def get_json(self):
		if not os.path.exists("offer_{}".format(self.offer_id)):
			os.makedirs("offer_{}".format(self.offer_id))
		with open("offer_{}/file.json".format(self.offer_id),"w",encoding='utf-8') as file:
			dictionary_object = {"title":self.title, "price":self.price, "link":self.link, "description":self.description, "details":self.details, "features":self.features}
			json_dump = json.dumps(dictionary_object,indent=3)
			try:
				file.write(json_dump)
			except UnicodeEncodeError:
				f.write(json_dump.encode('UTF8'))
			file.close()

	def get_photos(self):
		req = requests.get(self.link)
		soup = BeautifulSoup(req.text,'html.parser')
		slide = soup.find('div','css-1vp3jxc')
		photos = slide.find_all('img')
		thumbs = slide.find_all('figure','thumbsItem')
		urls_arr = []
		for thumb in thumbs:
			urls_arr.append(re.search('url\((.+?)\;',thumb.get('style')).group(1))
		i = 0
		for photo in urls_arr:
			urllib.request.urlretrieve(photo,"offer_{}/photo_{}.png".format(self.offer_id,i))
			i=i+1


def print_help():
	print("Niepoprawne wywołanie - spróbuj ponownie")
	print("Dozwolone parametry : [wynajem/sprzedaz] [mieszkanie/dom/dzialka/lokal/haleimagazyny/garaz] [miejscowosc]")


def check_args(operation, object_type, location):
	if operation not in {'wynajem','sprzedaz'} or object_type not in {'mieszkanie','dom','dzialka','lokal','haleimagazyny','garaz'}:
		print("Niepoprawne parametry")
		return False
	else:
		return True


def build_querystring(operation, object_type, location):
	querystring = 'http://www.otodom.pl/'+operation+'/'+object_type+'/'+location+'/'
	return querystring


if __name__ == '__main__':
	if(len(sys.argv)-1) == 3 and check_args(sys.argv[1],sys.argv[2],sys.argv[3]):
		querystring = build_querystring(sys.argv[1],sys.argv[2],sys.argv[3])
		req = requests.get(querystring)
		if req.status_code == 200:
			offer_id = 1
			offer_links_array = []
			offer_objects_array = []
			soup = BeautifulSoup(req.text, 'html.parser')
			pager = soup.find('ul','pager')
			max_page = int()
			if pager is not None:
				pagenums = pager.find_all('li')
				for li in pagenums:
					if li.text.isdigit():
						max_page=int(li.text)
			else:
				max_page = 1
			url_list = ["{}?page={}".format(querystring, str(page)) for page in range(1, max_page + 1)]
			for url in url_list:
				req = requests.get(url)
				if req.status_code == 200:
					soup = BeautifulSoup(req.text, 'html.parser')
					offers = soup.find_all('header','offer-item-header')
					for header in offers:
						links = header.find('a')
						offer_links_array.append(links.get('href'))
			for l in offer_links_array:
				offer_objects_array.append(Offer(l, offer_id))
				offer_id = offer_id + 1
	else:
		print_help()
