package Scraper;


import java.io.IOException;
import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrape {

    //Web Scrape Methode
    public void WebScrape(String url, PrintWriter pw) throws IOException {

        Document d = Jsoup.connect(url).maxBodySize(0).get();

        Elements storeName = d.select(".str-seller-card__store-name");

        //Loops Through Ebay Stores and Finds the Specified Items
        for (Element item : d.select(".s-item__info")) {
            
            Element title = item.parent().select(".s-item__title").first();
            if (!title.text().equals("Shop on eBay")) {
                pw.print(title.text().replaceAll(",", "") + ", ");
                Element price = item.parent().select(".s-item__price").first();
                pw.print(price.text().replaceAll(",", "") + ", ");
                Elements sold = item.parent().getElementsByClass("s-item__dynamic s-item__quantitySold");
                pw.print(sold.text().replaceAll(",", "").replaceAll("sold", "").replaceAll("\\+", "") + ", ");
                Element itmLnk = item.parent().select(".s-item__link").first();
                String itemLink = itmLnk.attr("href");
                pw.print(storeName.text() + ", ");
                pw.println(itemLink);
            }
            
        }
    }
}
