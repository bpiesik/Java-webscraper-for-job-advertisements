package com.webscraper.scraper;

import com.webscraper.model.JobAdvertisement;

import java.util.List;

public interface WebScraper {

    List<JobAdvertisement> getJobAdvertisements();


}
