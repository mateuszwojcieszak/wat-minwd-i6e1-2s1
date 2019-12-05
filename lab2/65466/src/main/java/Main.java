import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String buildType = args[0];
        String contractType = args[1];
        String minPrice = args[2];
        String maxPrice = args[3];
        String minSize = args[4];
        String maxSize = args[5];
        String roomNumber = args[6];

        String url = "https://www.otodom.pl/" + contractType + "/" + buildType + "/" +
                "?search%5Bfilter_float_price%3Afrom%5D=" + minPrice +
                "&search%5Bfilter_float_price%3Ato%5D=" + maxPrice +
                "&search%5Bfilter_float_m%3Afrom%5D=" + minSize +
                "&search%5Bfilter_float_m%3Ato%5D=" + maxSize +
                "&search%5Bfilter_enum_rooms_num%5D%5B0%5D=" + roomNumber +
                "&nrAdsPerPage=72";

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByClass("offer-item");

        StringBuilder result = new StringBuilder();
        result.append("[\n");
        for(Element element : elements){
            String title = element.getElementsByClass("offer-item-title").text();
            String link = element.attr("data-url");
            String location = element.getElementsByTag("p").text().split(": ")[1];
            String params = element.getElementsByClass("params").text();
            String jsonObject = "{\n\t\"title\":\"" + title + "\",\n\t\"link\":\""
                    + link + "\",\n\t\"location\":\"" + location + "\",\n\t\"params\":\"" + params + "\"\n}";
            if(elements.indexOf(element) == elements.size()-1){
                jsonObject += "\n]";
            }
            else{
                jsonObject += ",\n";
            }
            result.append(jsonObject);
        }
            FileWriter fileWriter = new FileWriter("houses.json");
            fileWriter.write(result.toString());
            fileWriter.flush();
    }
}
