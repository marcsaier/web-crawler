package webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

    public static void main(String[] args) {
        String url = args[0];

        try {
            Document webSite = Jsoup.connect(url).get();
            Pattern email = Pattern.compile("[a-zA-Z\\.]{2,20}@[a-zA-Z\\.-]{2,20}\\.[com|org|net|edu|de]{3}");
            Matcher matcher = email.matcher(webSite.toString());
            while (matcher.find()) {
                System.out.println(matcher.group());//Print email addresses to screen
            }
        }
        catch (IOException e) {
            System.err.println("A Error occured!");
            e.printStackTrace();
        }
    }
}
