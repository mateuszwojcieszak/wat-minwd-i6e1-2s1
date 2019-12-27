import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Application {

    private static final String PAGE_URL = "https://pl.wiktionary.org/wiki/Indeks:Angielski_-_Ssaki";
    private static final String HTTPS = "https:";

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> polishNames = new ArrayList<String>();
        ArrayList<String> englishNames = new ArrayList<String>();
        ArrayList<String> images = new ArrayList<String>();
        List<Item> items = new ArrayList<Item>();

        Document doc = Jsoup.connect(PAGE_URL).get();

        Elements elements = doc.select("tbody > tr > td");
        if (elements.isEmpty()) {
            throw new RuntimeException("No definitions found.");
        }


        for (Element e : elements) {
            String name = e.text();
//            System.out.println(name);
            if (!name.equals("")) {
                names.add(name);
            }
        }


import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Scraper {

    private static final String PAGE_URL = "https://pl.wiktionary.org/wiki/Indeks:Angielski_-_Ssaki";
    private static final String HTTPS = "https:";

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> polishNames = new ArrayList<String>();
        ArrayList<String> englishNames = new ArrayList<String>();
        ArrayList<String> images = new ArrayList<String>();
        List<Item> items = new ArrayList<Item>();

        Document doc = Jsoup.connect(PAGE_URL).get();

        Elements elements = doc.select("tbody > tr > td");
        if (elements.isEmpty()) {
            throw new RuntimeException("No definitions found.");
        }


        for (Element e : elements) {
            String name = e.text();
//            System.out.println(name);
            if (!name.equals("")) {
                names.add(name);
            }
        }

    }
}