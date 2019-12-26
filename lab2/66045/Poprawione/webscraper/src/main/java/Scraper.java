import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class Scraper {
    static int depth = 4;

    public static void main(String[] args) throws IOException {
        String jsonDataStringTwo = "{\"results\":[{\"Nazwa\":\"value\",\"Tweety\":\"value\",\"Obserwujący\":\"value\",,\"Obserwowani\":\"value\"}]}";
        JSONObject userObject = new JSONObject();
        JSONObject userValueObject = new JSONObject();
        JSONArray listUser = new JSONArray();

        URL url = new URL("https://twitter.com/");
        System.out.println("Podaj nazwę użytkownika którego informacje chcesz otrzymac");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();

        Document doc = Jsoup.connect(url + userName).get();

        userValueObject.put("Nazwa", userName);
        Elements stats = doc.getElementsByClass("ProfileNav-list");
        for (Element stat : stats) {
            Elements tweety = stat.getElementsByClass("ProfileNav-item ProfileNav-item--tweets is-active");
            for (Element tw : tweety) {
                userValueObject.put("Tweety", tw.getElementsByClass("ProfileNav-value").text());
            }

            Elements followers = stat.getElementsByClass("ProfileNav-item ProfileNav-item--followers");
            for (Element ff : followers) {
                userValueObject.put("Obserwujący", ff.getElementsByClass("ProfileNav-value").text());
            }

            Elements followed = stat.getElementsByClass("ProfileNav-item ProfileNav-item--following");
            for (Element fd : followed) {
                userValueObject.put("Obserwowani", fd.getElementsByClass("ProfileNav-value").text());
            }

            listUser.add(userValueObject);
            userObject.put("User", listUser);
            System.out.println(userObject);
            System.out.println("");
        }

        Elements tweets = doc.getElementsByClass("tweet");
        int depth = 0;
        for (Element tweet : tweets) {
            String jsonDataString = "{\"results\":[{\"Data publikacji\":\"value\",\"Treść tweeta\":\"value\",\"Odpowiedzi\":\"value\",,\"Retweety\":\"value\" ,\"Like\":\"value\"}]}";
            JSONObject mainObject = new JSONObject();
            JSONObject valuesObject = new JSONObject();
            JSONArray list = new JSONArray();
            if (depth < 5) {
                for (Element time : tweet.getElementsByClass("tweet-timestamp")) {
                    valuesObject.put("Data publikacji", time.getElementsByClass("_timestamp").text());
                }
                valuesObject.put("Treść tweeta", tweet.getElementsByClass("js-tweet-text-container").text());

                for (Element reply : tweet.getElementsByClass("ProfileTweet-action ProfileTweet-action--reply")) {
                    valuesObject.put("Odpowiedzi", reply.getElementsByClass("ProfileTweet-actionCountForPresentation").text());
                }
                int i = 0;
                for (Element retweet : tweet.getElementsByClass("ProfileTweet-action ProfileTweet-action--retweet js-toggleState js-toggleRt")) {
                    for (Element ret1 : retweet.getElementsByClass("ProfileTweet-actionCountForPresentation")) {
                        if (i == 0) {
                            valuesObject.put("Retweety", ret1.text());
                            i++;
                        }
                    }
                    int j = 0;
                    for (Element like : tweet.getElementsByClass("ProfileTweet-action ProfileTweet-action--favorite js-toggleState")) {
                        for (Element lik1 : retweet.getElementsByClass("ProfileTweet-actionCountForPresentation")) {
                            if (j == 0) {
                                valuesObject.put("Like", lik1.text());
                                j++;
                            }
                        }

                        list.add(valuesObject);
                        mainObject.put("Tweet", list);
                        System.out.println(mainObject);
                        depth++;
                    }
                }
            }
        }
            System.out.println("");
            System.out.println("Aby wyswietlic obserwujacych musisz się zalogować.");
        }
    }