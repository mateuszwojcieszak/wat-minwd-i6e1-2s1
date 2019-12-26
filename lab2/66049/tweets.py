from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.common.exceptions import NoSuchElementException

import json
import requests
import time

user = "joerogan"

def get_html_source(user):
    
    url = "https://twitter.com/" + user

    # Change Safari to desired browser
    driver = webdriver.Safari()
    driver.get(url)

    # From stackoverflow (https://stackoverflow.com/questions/20986631/how-can-i-scroll-a-web-page-using-selenium-webdriver-in-python)
    # ============================================================
    SCROLL_PAUSE_TIME = 1.0

    # Get scroll height
    last_height = driver.execute_script("return document.body.scrollHeight")

    while True:
        # Scroll down to bottom
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight)")

        # Wait to load page
        time.sleep(SCROLL_PAUSE_TIME)

        # Calculate new scroll height and compare with last scroll height
        new_height = driver.execute_script("return document.body.scrollHeight")

        # break condition
        if new_height == last_height:
            break
        last_height = new_height

    # ============================================================

    return driver.page_source

def get_tweets(page_source):
    content = BeautifulSoup(page_source, "html.parser")
    tweets_json = dict()
    tweets = list()

    tweet_divs = content.findAll("div", "tweet")
    for tweet_div in tweet_divs:
        action_count_list = tweet_div.find("div", "ProfileTweet-actionCountList") 
        tweets.append(
                {
                    "timestamp": tweet_div.find("a", "tweet-timestamp")["title"],
                    "replies_count": action_count_list.find("span", "ProfileTweet-action--reply").find("span", "ProfileTweet-actionCount")["data-tweet-stat-count"],
                    "retweets_count": action_count_list.find("span", "ProfileTweet-action--favorite").find("span", "ProfileTweet-actionCount")["data-tweet-stat-count"],
                    "favorites_count": action_count_list.find("span", "ProfileTweet-action--favorite").find("span", "ProfileTweet-actionCount")["data-tweet-stat-count"],
                    "content": tweet_div.find("p", "tweet-text").text
                }
        )

    tweets_json["user"] = user
    tweets_json["tweets"] = tweets

    with open(user + ".json", "w") as fp:
        json.dump(tweets_json, fp)

user = input("Enter twitter user name: ")
get_tweets(get_html_source(user))
