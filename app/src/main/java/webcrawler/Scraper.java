package webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper extends Thread {
    String url;
    ArrayList<String> followUrls = new ArrayList<>();
    ArrayList<String> eMails = new ArrayList<>();


    public Scraper (String url) {
        this.url = url;
    }

    @Override
    public void run () {
        try {
            Document webSite = Jsoup.connect(url).get();
            Pattern email = Pattern.compile("[a-zA-Z\\.]{2,20}@[a-zA-Z\\.-]{2,20}\\.[com|org|net|edu|de]{3}");
            Pattern link = Pattern.compile("href=\"([a-zA-Z\\./-:0-9]{5,25})\"");
            Matcher matcher = email.matcher(webSite.toString());
            Matcher matcher2 = link.matcher(webSite.toString());
            while (matcher.find()) {
                String match = matcher.group(1);
                eMails.add(match);
            }
            while (matcher2.find()) {
                String match = matcher2.group(1);
                followUrls.add(match);
            }
            for (int i=0; i<=followUrls.size(); i++) {
                System.out.println(followUrls.get(i));
            }
        }
        catch (IllegalStateException e) {
            System.err.println("Keine URLS gefunden auf dieser Seite");
        }
        catch (IOException e) {
            System.err.println("A Error occured!");
            e.printStackTrace();
        }
    }
    public ArrayList<String> getFollowUrls() {
        return followUrls;
    }
    public ArrayList<String> getEMails() {
        return eMails;
    }
}
