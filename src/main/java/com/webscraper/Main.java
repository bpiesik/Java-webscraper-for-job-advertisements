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

        JobAdvertisementSaverReader reader = new JobAdvertisementSaverReader();
        JobAdvertisement test = jobAdvertisements.get(0);
        System.out.println(test);
        reader.saveAsFile(test);
        JobAdvertisement test2 = reader.LoadFromFile();
        System.out.println(test2);
    }
}
