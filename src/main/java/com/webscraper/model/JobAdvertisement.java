package com.webscraper.model;

import java.io.*;

public class JobAdvertisement implements Serializable {
    private String title;
    private String company;
    private String[] locations;
    private String url;

    public JobAdvertisement(String title, String company, String[] locations, String url) {
        this.title = title;
        this.company = company;
        this.locations = locations;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "JobAdvertisment{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", locations=" + locations +
                ", url='" + url + '\'' +
                '}';
    }
}
