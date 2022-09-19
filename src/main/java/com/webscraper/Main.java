package com.webscraper;

import com.webscraper.model.JobAdvertisement;
import com.webscraper.scraper.StepStoneScraper;
import com.webscraper.scraper.WebScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        // filters
        List<String> ignoredCompanies = new ArrayList<>(
                List.of("Test Company Name")
        );

        WebScraper stepStone = new StepStoneScraper();
        List<JobAdvertisement> jobAdvertisements = stepStone.getJobAdvertisements();

        List<JobAdvertisement> filteredJobAdvertisements = jobAdvertisements.stream()
                .filter(ad -> !ignoredCompanies.contains(ad.getCompany()))
                .toList();

        for (JobAdvertisement ad : filteredJobAdvertisements) {
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runtime rt = Runtime.getRuntime();
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + "https://" + ad.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
