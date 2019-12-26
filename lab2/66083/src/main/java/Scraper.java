import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

    public static void main(String[] args) {
        try {
            String language2 = "angielski";
            String value;
            String word;
            String photo = "photo";

            PrintStream o = new PrintStream(new File("file.json"));

            PrintStream console = System.out;

            Document doc = Jsoup.connect("https://pl.wiktionary.org/wiki/Indeks:Angielski_-_Owoce?fbclid=IwAR1yG-CLvHehOIY5L-Gh9Bl0-P1WyGSwkTO79gAqoZe1iwCXa68BGZIPggg").get();

            Elements rows = doc.select("tr");
            for (Element row: rows) {
                value = row.text();
                String[] splitted = value.split(" ");
                word = splitted[0];
                Elements columns = row.select("td");
                for (Element column: columns) {
                    language2= column.text();
                    photo= "/url/" + language2 + ".jpg";

                }
                System.out.println();



                Model model = new Model();

                model.setLanguage1(word);
                model.setLanguage2(language2);
                model.setUrl(photo);

                ObjectMapper mapper = new ObjectMapper();

                String jsonString = mapper.writeValueAsString(model) ;

                System.setOut(o);

                System.out.println(jsonString);


                System.setOut(console);
                System.out.println("This will be written on the console!");
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
