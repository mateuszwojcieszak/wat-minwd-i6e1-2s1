import io
import os
import sys
from urllib.error import HTTPError
from urllib.request import urlopen

import bs4 as soup
import jsons
import wget


def main():
    input = get_input()
    for url in input:
        print(url)
    print(type(input))
    print(len(input))
    for url in input:
        scrap(url)


def read_input_urls_from_file(filepath):
    file = open(filepath, 'r')
    urls = file.readlines()
    return urls


def get_input():
    input = []
    if len(sys.argv) < 2:
        return input
    else:
        filepath = sys.argv[1]
        return read_input_urls_from_file(filepath)
    return input


def scrap(url):
    response = urlopen(url)
    bsObj = bsObj = soup.BeautifulSoup(response.read(), 'lxml')
    model = Model()
    model.language1 = "polski"
    model.language2 = "angielski"
    file_name = create_file_name(bsObj.head.title.getText())
    with io.open(file_name, 'w+', encoding='utf-8') as openedFile:
        for tableRow in bsObj.find_all('tr'):
            word = get_word_and_save_photo(tableRow, file_name)
            model.words.append(word)
        openedFile.write(jsons.dumps(model))


def create_file_name(string):
    # File name can't contain: \ / * ? < > | & 
    # Let's convert those chars for _ 
    # File name can't be longer that 260 on windows (i think) but lets cut it to 50 chars to make it more beautiful
    outputFileName = string.replace("\\", "_").replace("/", "_").replace("*", "_").replace("?", "_").replace("<",
                                                                                                             "_").replace(
        ">", "_").replace("|", "_").replace(":", "_").replace("&", "_")
    outputFileName = outputFileName[:50]
    current_work_directory = os.getcwd()
    return "./output/" + outputFileName + ".json"


def save_photo(photo_address, filename):
    address = photo_address.split()
    url = 'https:' + address[0]
    # image = urllib.URLopener()
    # image.retrieve(url)
    # local_file = open('local_image.jpg', 'wb')
    # shutil.copyfileobj(image, local_file)
    try:
        local_image_filename = wget.download(url, './output/')
        return '/output/' + local_image_filename
    except (IndexError, HTTPError):
        print('\nerror occured')
        return '?'


def get_word_and_save_photo(table_row, filename):
    word = Word()
    cells = table_row.find_all('td')
    if len(cells) == 0:
        return Word()
    if len(cells) == 3:  # with photo
        word.language1 = cells[0].getText()
        word.language2 = cells[2].getText()
        img = cells[1].find_all('img')
        if len(img) > 0:
            photo_adress = img[0]['srcset']
            word.photo = save_photo(photo_adress, filename)
        else:
            word.photo = 'null'
        return word
    if len(cells) == 2:
        word.language1 = cells[0].getText()
        word.language2 = cells[1].getText()
        word.photo = "null"
        return word


class Model:
    language1 = ""
    language2 = ""
    words = []


class Word:
    language1 = ""
    language2 = ""
    photo = "null"


main()
