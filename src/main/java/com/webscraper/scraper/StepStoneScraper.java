package com.webscraper.scraper;

import com.webscraper.JobAdvertisementSaverReader;
import com.webscraper.model.JobAdvertisement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StepStoneScraper implements WebScraper {

    /**
     * Scrapes stepstone for job advertisements and returns the HTML page as a Document
     * @return Document containing the HTML page
     */
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

    /**
     * Scrapes the HTML file of stepstone job advertisements for information like job title,
     * company name, location and url, saves them into job advertisement objects and
     * returns them
     * @return List<JobAdvertisement> a List with all found job advertisements
     */
    @Override
    public List<JobAdvertisement> getJobAdvertisements() {
        // get HTML file
        Document doc = getDocumentFromUrl();

        // search for job advertisements
        Elements ads = doc.getElementsByClass("Wrapper-sc-11673k2-0 cmgNOQ");

        // create models
        List<JobAdvertisement> jobAdvertisements = new ArrayList<>();

        for (Element ad : ads) {
            Elements title = ad.getElementsByClass("resultlist-12iu5pk");
            Elements companyName = ad.getElementsByClass("resultlist-1v262t5");
            Elements locations = ad.getElementsByClass("resultlist-12dlfn3");
            Elements url = ad.getElementsByClass("resultlist-12iu5pk");
            String urlTxt = "stepstone.de" + url.attr("href");

            JobAdvertisement jobAdvertisement = new JobAdvertisement(
                    title.text(),
                    companyName.text(),
                    locations.text().split(" "),
                    urlTxt
            );

            jobAdvertisements.add(jobAdvertisement);
        }
        return jobAdvertisements;
    }

    /**
     * Deletes all found job advertisements that were already visited/seen in last crawl
     * @return List<JobAdvertisement> a list with new job advertisements
     */
    public List<JobAdvertisement> getNewestJobAdvertisements(){
        List<JobAdvertisement> jobAdvertisements = getJobAdvertisements();
        JobAdvertisement lastJobAdvertisement = getLastVisitedJobAdvertisement();

        return jobAdvertisements
                .stream()
                // url is used as a unique identifier in this case
                .takeWhile(ad -> !lastJobAdvertisement.getUrl().equals(ad.getUrl()))
                .toList();

    }

    /**
     * Saves the last visited job advertisement that was already visited the last time
     * when scraping the advertisements
     */
    public void saveLastVisitedJobAdvertisement(JobAdvertisement jobAdvertisement){
        new JobAdvertisementSaverReader().saveAsFile(jobAdvertisement);
    }

    /**
     * Returns the job advertisement that was already visited the last time
     * when scraping the advertisements
     * If there is not one because the system runs for the first time it will
     * return the first one it found while scraping and save it for the next run
     * @return JobAdvertisement (see above)
     */
    public JobAdvertisement getLastVisitedJobAdvertisement(){
        JobAdvertisement lastVisited = new JobAdvertisementSaverReader().LoadFromFile();

        if(lastVisited.getTitle().equals("placeholder")){
            List<JobAdvertisement> jobAdvertisements = getJobAdvertisements();
            JobAdvertisement lastElement = jobAdvertisements.get(jobAdvertisements.size() - 1);
            saveLastVisitedJobAdvertisement(jobAdvertisements.get(0));
            return lastElement;

        }
        return lastVisited;
    }

}
