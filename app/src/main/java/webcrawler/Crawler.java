package webcrawler;


import java.util.ArrayList;
import java.util.Collections;

public class Crawler {

    public static void main(String[] args) {
        ArrayList<String> urls = new ArrayList<String>();
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> eMails = new ArrayList<String>();
        urls.add(args[0]);
        try {
            while (urls.size() > 0) {
                for (int i=0; i < urls.size(); i++) {
                    Scraper scraper = new Scraper(urls.get(i));
                    scraper.start();
                    visited.add(urls.get(i));
                    urls.set(i, null);
                    scraper.join();
                    ArrayList<String> followUrls;
                    followUrls = scraper.getFollowUrls();
                    for (String url: followUrls) {
                        if (!visited.contains(url)) {
                            urls.add(url);
                        }
                    }
                    ArrayList<String> eMailRaw;
                    eMailRaw = scraper.getEMails();
                    for (String email : eMailRaw) {
                        if (!eMails.contains(email)) {
                            eMails.add(email);
                        }
                    }
                }
                urls.removeAll(Collections.singleton(null));
            }
        }
        catch (Exception e) {
            System.err.println("Something went wrong!");
        }
    }
}
