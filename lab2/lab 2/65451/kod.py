import requests, bs4, sys, csv, datetime

now = datetime.datetime.now()

print('Podaj marke:')
maker = input()
print('Podaj model:')
model = input()

path = 'https://www.otomoto.pl/osobowe/' + maker + '/' + model 

res = requests.get(path)
res.raise_for_status()

carSoup = bs4.BeautifulSoup(res.text, features="lxml")
lastPage = int(carSoup.select('.page')[-1].text)

for i in range(1, lastPage):
    res = requests.get(path + '?page=' + str(i))
    res.raise_for_status()
    currentPage = bs4.BeautifulSoup(res.text, features='lxml')
    carList = currentPage.select('article.offer-item')
    print("parsing page " + str(i))
    for car in carList:
        currentCarData = []
        price = car.find('span',class_='offer-price__number').text.strip().replace(" ", "")
        currentCarData.append(price)
        title = car.find('a',class_='offer-title__link').text.strip()
        currentCarData.append(title)

        paramList = ["year", "mileage", "engine_capacity", "fuel_type"]
        for param in paramList:
            currentParameter = car.find('li', {"data-code": param})
            if (currentParameter):
                currentCarData.append(currentParameter.text.strip())
                print(currentParameter.text.strip())
            else:
                currentCarData.append("")
