package com.webscraper;

import com.webscraper.model.JobAdvertisement;
import com.webscraper.scraper.StepStoneScraper;
import com.webscraper.scraper.WebScraper;

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
            System.out.println(ad.getCompany());
        }
    }
}
