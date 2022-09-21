package com.webscraper;

import com.webscraper.model.JobAdvertisement;
import com.webscraper.scraper.StepStoneScraper;
import com.webscraper.scraper.WebScraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * to do:
 * - verschiedene keywords/suchbegriffe angeben können
 * - andere job plattformen anschließen
 */
public class Main {
    public static void main(String[] args) {
        // parameters
        boolean openInBrowser = true;

        // filters
        List<String> ignoredCompanies = new ArrayList<>(
                List.of("Test Company")
        );
        List<String> ignoredLocations = new ArrayList<>(
                List.of("München")
        );
        List<String> ignoredKeywords = new ArrayList<>(
                List.of("Senior",
                        "(Senior)",
                        "PHP",
                        "C#",
                        "Embedded")
        );

        // get job advertisements
        WebScraper stepStone = new StepStoneScraper();
        List<JobAdvertisement> jobAdvertisements = stepStone.getNewestJobAdvertisements();

        // apply filters
        List<JobAdvertisement> filteredJobAdvertisements = jobAdvertisements.stream()
                .filter(ad -> !ignoredCompanies.contains(
                        ad.getCompany()))
                .toList();

        for (String keyword : ignoredKeywords) {
            filteredJobAdvertisements = filteredJobAdvertisements.stream()
                    .filter(
                            jobAdvertisement -> !Arrays.stream(jobAdvertisement.getTitle().split(" ")).
                                    toList().contains(keyword))
                    .toList();
        }

        filteredJobAdvertisements.forEach(System.out::println);

    if(openInBrowser){
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
}
