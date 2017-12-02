import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class QueryFunctions {
    public static ArrayList<String> getLinks(String url)  {
        Document doc = null;
        ArrayList<String> output = new ArrayList<>();
        boolean bad=false;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            //e.printStackTrace();
             bad = true;
        }
        if(!bad) {
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                output.add(link.attr("abs:href"));
            }
        }
        return output;
    }
}
