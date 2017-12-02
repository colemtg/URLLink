
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class AGreatIdea {
    public static void main (String args[]) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Greetings world");
        HashSet<String> idea= new HashSet<>();
        System.out.println("Great ideas if true: " + idea.isEmpty());
        if(true == true || !(true == true))
        {
            System.out.println("Identity proved that this is this");
        }
        Date testDate = new Date();
        System.out.println(testDate.getTime());

        String start,end,check="";
        boolean toCheck=false;
        System.out.println("Do you want to filter on a prefix? 1 for yes, anything else is no");
        if(input.nextLine().equals("1"))
        {
            toCheck=true;
        }
        if(toCheck)
        {
            System.out.println("enter your prefix");
            check=input.nextLine().trim();
        }
        System.out.println("enter start URL: ");
        start=input.nextLine();
        while(extractUrlsFromString(start,check,toCheck).isEmpty())
        {
            System.out.println("invalid URL, try again");
            System.out.println("enter start URL: ");
            start=input.nextLine();
        }

        System.out.println("enter end URL: ");
        end=input.nextLine();
        while(extractUrlsFromString(end,check,toCheck).isEmpty())
        {
            System.out.println("invalid URL, try again");
            System.out.println("enter end URL: ");
            end=input.nextLine();
        }

        QueryURL test1 = new QueryURL(start.trim(),end.trim(),check,toCheck);


        QueryURL test = new QueryURL("https://en.wikipedia.org/wiki/Positive",
                "https://en.wikipedia.org/wiki/Ruins","wikipedia.org",true);
        ArrayList<String> output = test1.process();
        if(output.size()==1)
        {
            System.out.println("no path found");
        }
        else {
            for (int i = 0; i < output.size(); i++) {
                if (i == 0) {
                    System.out.println("From " + output.get(i));
                    //System.out.println(output.get(i));
                } else if (i != output.size() - 1) {
                    System.out.println("click " + output.get(i) + " then");
                } else {
                    System.out.println("click " + output.get(i));
                }


            }
        }


    }

    public static ArrayList<String> extractUrlsFromString(String content,String check, boolean toCheck)
    {
        ArrayList<String> result = new ArrayList<String>();

        String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find())
        {
            if(toCheck)
            {
                if(m.group().contains(check))
                {
                    result.add(m.group());
                }
            }
            else
            {
                result.add(m.group());
            }
        }

        return result;
    }
}
