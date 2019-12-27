using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HtmlAgilityPack;
using System.Net.Http;
using System.Text.RegularExpressions;

namespace CrawlerDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            //Console.WriteLine("Hello World!");
            startCrawlerasync();
            Console.ReadLine();
        }


        private static async Task startCrawlerasync()
        {
            var url = "https://twitter.com/BoniekZibi";
            var httpClient = new HttpClient();
            var html = await httpClient.GetStringAsync(url);
            var htmlDocument = new HtmlDocument();
            htmlDocument.LoadHtml(html);

            var twitts = new List<Twitt>();
            var divs =htmlDocument.DocumentNode.Descendants("div").Where(node => node.GetAttributeValue("class", "").Equals("js-tweet-text-container")).ToList();
            Twitt twitt = new Twitt();
            foreach (var div in divs)
            {
                

                    twitt.tresc = div.Descendants("TweetTextSize TweetTextSize--normal js-tweet-text tweet-text").FirstOrDefault().InnerText;

                    twitts.Add(twitt);
            }
            
            try
            {
                foreach (Twitt a in twitts)
                {
                    Console.WriteLine();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Nie dziala");
            }
            }
            
        


        
        public class Twitt
        {

            public String tresc;
            


        }


    }
}
