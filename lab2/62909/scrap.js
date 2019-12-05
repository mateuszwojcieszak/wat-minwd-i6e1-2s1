const cheerio = require("cheerio");
const axios = require("axios");
const fs = require("fs");

const siteUrl =
  "https://www.otodom.pl/sprzedaz/mieszkanie/?locations%5B0%5D%5Bregion_id%5D=11&locations%5B0%5D%5Bsubregion_id%5D=278&locations%5B0%5D%5Bcity_id%5D=206&locations%5B1%5D%5Bregion_id%5D=11&locations%5B1%5D%5Bsubregion_id%5D=439&locations%5B1%5D%5Bcity_id%5D=40&locations%5B2%5D%5Bregion_id%5D=11&locations%5B2%5D%5Bsubregion_id%5D=280&locations%5B2%5D%5Bcity_id%5D=208.pl/sprzedaz/mieszkanie/sopot/?search%5Bregion_id%5D=11&search%5Bsubregion_id%5D=280&search%5Bcity_id%5D=208";

const title = []; // Tytuł ogłoszenia
const location = []; // Lokalizacja
const cena = []; // Cena całkowita
const liczbaPokoi = []; // Liczba Pokoi
const metry = []; // Liczba metrów
const cenaZaMetr = []; // Cena za metr

const fetchData = async () => {
  const result = await axios.get(siteUrl);
  return cheerio.load(result.data);
};

const getResults = async () => {
  const $ = await fetchData();

  $("div.offer-item-details > header > h3 > a > span > span").each(function() {
    title.push($(this).text());
  });

  $("div.offer-item-details > header > p").each(function() {
    location.push($(this).text());
  });

  $("div.offer-item-details > ul > li.offer-item-price").each(function() {
    cena.push($(this).text());
  });

  $("div.offer-item-details > ul > li.offer-item-rooms.hidden-xs").each(
    function() {
      liczbaPokoi.push($(this).text());
    }
  );

  $("div.offer-item-details > ul > li.hidden-xs.offer-item-area").each(
    function() {
      metry.push($(this).text());
    }
  );

  $("div.offer-item-details > ul > li.hidden-xs.offer-item-price-per-m").each(
    function() {
      cenaZaMetr.push($(this).text());
    }
  );

  const locattt = location.map(loc =>
    loc.replace("Mieszkanie na sprzedaż: ", "")
  );
  const cenaaa = cena.map(cn => cn.replace(/\s+/g, ""));

  var mieszkanie = {};

  for (var x = 0; x < title.length; x++) {
    mieszkanie[x] = {
      tytul: title[x],
      lokalizacja: locattt[x],
      cenaCalkowita: cenaaa[x],
      liczbaPokoi: liczbaPokoi[x],
      liczbaMetrow: metry[x],
      cenaZaMetr: cenaZaMetr[x]
    };
  }

  let jsonString = JSON.stringify(mieszkanie);
  fs.writeFileSync("./output.json", jsonString, "utf-8");

  console.log(mieszkanie);
  return mieszkanie;
};

getResults();

module.exports = getResults;
