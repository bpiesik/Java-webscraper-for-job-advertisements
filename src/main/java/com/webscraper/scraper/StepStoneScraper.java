package com.webscraper.scraper;

import com.webscraper.model.JobAdvertisement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepStoneScraper implements WebScraper {

    public Document getDocumentFromUrl() {
        try {
            return Jsoup.connect("https://www.stepstone.de/jobs/java-backend-entwickler?" +
                    "&sort=2" +
                    "&action=sort_publish").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<JobAdvertisement> getJobAdvertisements() {
        // get HTML file
        Document doc = getDocumentFromUrl();

        // search for job advertisements
        Elements ads = doc.getElementsByClass("Wrapper-sc-11673k2-0 cmgNOQ");

        // create models
        List<JobAdvertisement> jobAdvertisments = new ArrayList<>();

        for (Element ad : ads) {
            Elements title = ad.getElementsByClass("resultlist-12iu5pk");
            Elements companyName = ad.getElementsByClass("resultlist-1v262t5");
            Elements locations = ad.getElementsByClass("resultlist-12dlfn3");
            Elements url = ad.getElementsByClass("resultlist-12iu5pk");
            String urlTxt = "stepstone.de" + url.attr("href");

            JobAdvertisement jobAdvertisment = new JobAdvertisement(
                    title.text(),
                    companyName.text(),
                    locations.text().split(" "),
                    urlTxt
            );

            jobAdvertisments.add(jobAdvertisment);
        }
        return jobAdvertisments;
    }
}
