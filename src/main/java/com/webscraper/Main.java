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
        // filters
        List<String> ignoredCompanies = new ArrayList<>(
                List.of("Test Company")
        );
        List<String> ignoredLocations = new ArrayList<>(
                List.of("Test Location")
        );
        List<String> ignoredKeywords = new ArrayList<>(
                List.of("Senior",
                        "(Senior)",
                        "PHP",
                        "C#")
        );

        WebScraper stepStone = new StepStoneScraper();
        List<JobAdvertisement> jobAdvertisements = stepStone.getNewestJobAdvertisements();

        List<JobAdvertisement> filteredJobAdvertisements = jobAdvertisements.stream()
                .filter(ad -> !ignoredCompanies.contains(ad.getCompany())
                        || !ignoredLocations.contains(ad.getLocations()))
                .toList();

        for (String keyword : ignoredKeywords) {
            filteredJobAdvertisements = filteredJobAdvertisements.stream()
                    .filter(
                    jobAdvertisement -> !Arrays.stream(jobAdvertisement.getTitle().split(" ")).
                            toList().contains(keyword))
                    .toList();
        }

        filteredJobAdvertisements.forEach(System.out::println);

        /*for (JobAdvertisement ad : filteredJobAdvertisements) {
            Runtime rt = Runtime.getRuntime();
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + "https://" + ad.getUrl())
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
