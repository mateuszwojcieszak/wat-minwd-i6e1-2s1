import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static jdk.nashorn.internal.objects.Global.print;

public class Scraper {

    public static void main(String[] args) {

        int work = 1;
        int choice;
        Scanner input = new Scanner(System.in);

        while (work == 1) {

            System.out.println("Wybierz spośród: ");
            System.out.println("Chcę informacje ogólne o dostępnych ofertach   (1)");
            System.out.println("Chcę informacje szczegółowe o wybranej ofercie (2)");
            System.out.println("Wyjście                                        (3)");
            System.out.println("Wprowadź wybór: ");

            choice = input.nextInt();


            switch (choice) {

                case 1:

                    try {






/*

//          Niedzialajacy fragment kody odpowiadajacy za logowanie do linkedin

            String url = "https://www.linkedin.com/uas/login?goback=&trk=hb_signin";
            Connection.Response response = Jsoup
                    .connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            Document responseDocument = response.parse();
            Element loginCsrfParam = responseDocument
                    .select("input[name=loginCsrfParam]")
                    .first();

            response = Jsoup.connect("https://www.linkedin.com/uas/login-submit")
                    .cookies(response.cookies())
                    .data("loginCsrfParam", loginCsrfParam.attr("value"))
                    .data("session_key", "email")
                    .data("session_password", "password")
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute();

            Document document = response.parse();

//                        System.out.println(document);

            System.out.println("Welcome "
                    + document.select(".act-set-name-split-link").html());

*/


                        PrintStream o = new PrintStream(new File("json_ogolne.json"));

                        // Store current System.out before assigning a new value
                        PrintStream console = System.out;

                        Scanner sc = new Scanner(System.in);
                        System.out.printf("Wejdz na strone: https://pl.linkedin.com/?trk=guest_job_search_nav-header-logo\n");
                        System.out.printf("Wybierz interesujaca cie lokalizacje i wcisnij przycisk 'Search Jobs' \n");
                        System.out.printf("Podaj adres url strony na ktora zostaniesz przeniesiony: \n");

                        String name = sc.nextLine();
//                        System.out.println(name);
                        name = name.substring(8);
                        System.out.println(name);

                        Document doc = Jsoup.connect("https://" + name).get();
                        System.out.printf("Tytuł: %s\n", doc.title());


                        // Lista danych firmy
                        Elements infos = doc.getElementsByClass("result-card__contents job-result-card__contents");
//                        System.out.println(infos);

                        for (Element information : infos) {

                            String company = information.getElementsByClass("result-card__subtitle-link job-result-card__subtitle-link").text();

                            String job = information.getElementsByClass("result-card__title job-result-card__title").text();

                            String location = information.getElementsByClass("job-result-card__location").text();

                            String snippet = information.getElementsByClass("job-result-card__snippet").text();

                            String date = information.getElementsByClass("job-result-card__listdate").text();

                            Item item = new Item();

                            item.setCompany(company);
                            item.setJob(job);
                            item.setLocation(location);
                            item.setSnippet(snippet);
                            item.setDate(date);

                            ObjectMapper mapper = new ObjectMapper();
//                mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
                            String jsonString = mapper.writeValueAsString(item);


                            // Assign o to output stream
                            System.setOut(o);
//                            System.out.println("This will be written to the text file");
                            System.out.println(jsonString);
                            // Use stored value for output stream

                            System.setOut(console);
//                        System.out.println("This will be written on the console!");


                            System.out.println("Nazwa firmy: " + company);
                            System.out.println("\t" + "Stanowisko: " + job);
                            System.out.println("\t" + "Lokalizacja: " + location);
                            System.out.println("\t" + "Dodatkowe informacje: " + snippet);
                            System.out.println("\t" + "Data dodania: " + date);
                            System.out.println("\n");

                        }

                        // In case of any IO errors, we want the messages written to the console
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;


                case 2:

/*
                    Niedzialajacy kod do pobierania informacji szczegolowych do poszczegolnych ofert
                    czesc kodu html jest nie wykrywana i
                    pobieranie elementow ze strony pomija czesc z potrzebnymi danymi
*/

                    try{

                    Scanner sc = new Scanner(System.in);
                    System.out.printf("Wejdz na strone: https://pl.linkedin.com/?trk=guest_job_search_nav-header-logo\n");
                    System.out.printf("Wybierz interesujaca cie lokalizacje i wcisnij przycisk 'Search Jobs' \n");
                    System.out.printf("Wybierz interesujaca cie firmę \n");
                    System.out.printf("Podaj adres url aktualnej strony: \n");

                    String name = sc.nextLine();
//                    System.out.println(name);
                    name = name.substring(8);
                    System.out.println(name);


//                    String url = "https://" + name;
//                    System.out.println(url);
//                    Document doc = Jsoup.parse(url);

                    Document doc = Jsoup.connect("https://" + name).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0").get();
                    System.out.printf("Tytuł: %s\n", doc.title());

                   Elements infos = doc.select("section.results__detail-view");
//                    Elements infos = doc.getElementsByClass("results__detail-view");
                    System.out.println(infos);

//                        results__detail-view"
//                        large-loader
//                        details-pane__content
                    Elements infos2 = doc.getElementsByClass("topcard__content");

                    Elements inf = doc.select("results__detail-view");

                    for (Element information : infos) {

                        String company = information.getElementsByClass("topcard__org-name-link").text();
                        System.out.println(information.getElementsByClass("topcard__org-name-link").text());
                        String job = information.getElementsByClass("topcard__title").text();
                        System.out.println(job);
                        String location = information.getElementsByClass("topcard__flavor topcard__flavor--bullet").text();
                        System.out.println(location);
                        String date = information.getElementsByClass("topcard__flavor--metadata posted-time-ago__text").text();
                        System.out.println(date);
                        String candidates = information.getElementsByClass("topcard__flavor--metadata topcard__flavor--bullet num-applicants__caption").text();
                        System.out.println(candidates);
//                            String location = information.getElementsByClass("job-result-card__location").text();
//
//                            String snippet = information.getElementsByClass("job-result-card__snippet").text();
//
//                            String date = information.getElementsByClass("job-result-card__listdate").text();
//
//
                        System.out.println("description: " + company);
                        System.out.println("\t" + "Stanowisko: " + job);
                        System.out.println("\t" + "Lokalizacja: " + location);
                        System.out.println("\t" + "Data dodania: " + date);
                        System.out.println("\t" + "Liczba kandydatów: " + candidates);
                        System.out.println("\n");
//
                    }

                    // In case of any IO errors, we want the messages written to the console

                    }catch (IOException e) {
                    e.printStackTrace();
                }


                    break;

                case 3:
                    System.out.println("Koniec");
                    System.exit(0);
            }
        }
    }

}
