package com.webscraper;

import com.webscraper.model.JobAdvertisement;
import com.webscraper.scraper.StepStoneScraper;
import com.webscraper.scraper.WebScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebScraper stepStone = new StepStoneScraper();
        List<JobAdvertisement> jobAdvertisements = stepStone.getJobAdvertisements();

        for(JobAdvertisement ad : jobAdvertisements){
            System.out.println(ad.getTitle());
        }
    }
}
