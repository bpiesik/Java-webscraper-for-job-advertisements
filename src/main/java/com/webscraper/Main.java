package com.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // params
        int pageNumber = 1;

        // get file
        Document doc = Jsoup.connect("https://www.stepstone.de/jobs/java-backend-entwickler?" +
                "page=" + pageNumber +
                "&sort=2" +
                "&action=sort_publish").get();

        // get job advertisements
        Elements ads = doc.getElementsByClass("Wrapper-sc-11673k2-0 cmgNOQ");

        for (Element ad : ads){
            // title
            Elements title = ad.getElementsByClass("resultlist-12iu5pk");
            System.out.println("Title: " + title.text());

            // company name
            Elements companyName = ad.getElementsByClass("resultlist-1v262t5");
            System.out.println("Company Name: " + companyName.text());

            // location
            Elements locations = ad.getElementsByClass("resultlist-12dlfn3");
            System.out.println("Location: " + locations.text());

            // url
            Elements url = ad.getElementsByClass("resultlist-12iu5pk");
            String urlTxt = "stepstone.de" + url.attr("href");
            System.out.println("url: " + urlTxt);



            System.out.println("\n");
        }


    }
}
