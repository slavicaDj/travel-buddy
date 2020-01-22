package clients.feed;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import model.Feed;
import util.Util;

public class FeedClient {

	public static String FEED_URL = "http://www.faroo.com/api?q=travel&l=en&src=news&f=rss";
	
	public static ArrayList<Feed> getFeeds() {
		
		ArrayList<Feed> feeds = new ArrayList<>();	
		
        try {
            Document docTry = Jsoup.parse(new URL(FEED_URL),10000);
            List<Element> items = docTry.getElementsByTag("item");
            
            for (Element item : items) {
            	            	
            	String title = item.getElementsByTag("title").first().text();
            	String link = item.getElementsByTag("link").first().text();
      
            	String htmlToParse = item.getElementsByTag("description").first().text();
                Document doc = Jsoup.parse(htmlToParse);	
                
                String description = doc.body().ownText();	   
                String descriptionLink = null;
                String descriptionImageLink = null;
                
                Element element = doc.select("a").first();
                if (element != null) {
  
                	descriptionLink = element.attr("href");
                	descriptionImageLink = element.getElementsByTag("img").get(0).attr("src");
                
                }
                
                String pubDateString = item.getElementsByTag("pubDate").first().text();
                Date pubDate = Util.parseDate(pubDateString, "EEE, dd MMM yyyy kk:mm:ss");
                
            	Feed feed = new Feed();
            	feed.setDescription(description);
            	feed.setDescriptionImageLink(descriptionImageLink);
            	feed.setDescriptionLink(descriptionLink);
            	feed.setLink(link);
            	feed.setPubDate(pubDate);
            	feed.setTitle(title);
            	
            	feeds.add(feed);
            	
            	if (feeds.size() == 3) {
            		return feeds;
            	}
            	            	
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return feeds;
	}

}
