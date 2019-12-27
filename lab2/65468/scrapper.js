const rp = require("request-promise");
const $ = require("cheerio");
const url = "https://pl.wiktionary.org/wiki/Indeks:Angielski_-_Las";

rp(url)
  .then(function(html) {
    //success!
    const long1 = $("td:nth-child(1) > a", html).length;
    const long2 = $("td:nth-child(2) > a > img", html).length;

    const pol = [];
    const img = [];
    const ang = [];

    for (let i = 0; i < long1; i++) {
      pol.push($("td:nth-child(1) > a", html)[i].attribs.title);
      ang.push($("td:nth-child(3) > a", html)[i].attribs.title);
    }

    for (let i = 0; i < long2; i++) {
      img.push($("td:nth-child(2) > a > img", html)[i].attribs.src);
    }

    // console.log(pol);
    console.log(img);
    // console.log(ang);

    var objects = {};

    for (var x = 0; x < long1; x++) {
      objects[x] = { pol: pol[x], ang: ang[x] };
    }

    console.log(objects);
  })
  .catch(function(err) {
    //handle error
  });
