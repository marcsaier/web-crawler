package webcrawler;




public class Crawler {

    public static void main(String[] args) {
        String url = args[0];

        try {
            Scraper scraper = new Scraper(url);
            scraper.start();
            
        }
        catch (Exception e) {
            System.err.println("Something went wrong!");
        }
        
    }
}
