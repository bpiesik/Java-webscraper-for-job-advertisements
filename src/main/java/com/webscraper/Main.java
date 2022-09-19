package com.webscraper;

import com.webscraper.model.JobAdvertisement;
import com.webscraper.scraper.StepStoneScraper;
import com.webscraper.scraper.WebScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // filters
        List<String> ignoredCompanies = new ArrayList<>(
                List.of("Test Company Name")
        );
        List<String> ignoredLocations = new ArrayList<>(
                List.of("Test Location")
        );

        WebScraper stepStone = new StepStoneScraper();
        List<JobAdvertisement> jobAdvertisements = stepStone.getNewestJobAdvertisements();

        List<JobAdvertisement> filteredJobAdvertisements = jobAdvertisements.stream()
                .filter(ad -> !ignoredCompanies.contains(ad.getCompany())
                        && !ignoredLocations.contains(ad.getLocations()))
                .toList();

        filteredJobAdvertisements.forEach(System.out::println);

        for (JobAdvertisement ad : filteredJobAdvertisements) {
            Runtime rt = Runtime.getRuntime();
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + "https://" + ad.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
