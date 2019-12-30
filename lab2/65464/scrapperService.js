const $ = require('cheerio');

function scrapDataTwitter(o1) {
    const tweets = $('.ProfileNav-value', o1);
    const nickname = $('.ProfileHeaderCard-name > .ProfileHeaderCard-nameLink', o1)[0]['children'][0]['data'];
    const description = $('.ProfileHeaderCard-bio', o1)[0]['children'][0]['data'];
    const location = $('.ProfileHeaderCard-location > .ProfileHeaderCard-locationText', o1)[0]['children'][0]['data'].trim();
    const homePage = $('.ProfileHeaderCard-urlText > .u-textUserColor', o1)[0]['attribs']['title'];
    const tweetsQuantity = tweets[0]['attribs']['data-count']
    const observe = tweets[1]['attribs']['data-count']
    const fallowers = tweets[2]['parent']['attribs']['title']
    const likes = tweets[3]['attribs']['data-count']
    const response = {
        "tweets": tweetsQuantity,
        "observe": observe,
        "fallowers": fallowers,
        "likes": likes,
        "nickname": nickname,
        "description": description,
        "location": location,
        "homePage": homePage,
    }
    return response
}

module.exports = {
    scrapDataTwitter
}