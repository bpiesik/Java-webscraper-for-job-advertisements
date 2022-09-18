package com.webscraper;

import java.util.List;

public class JobAdvertisment {
    private String title;
    private String company;
    private List<String> locations;
    private String url;

    public JobAdvertisment(String title, String company, List<String> locations, String url) {
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
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
