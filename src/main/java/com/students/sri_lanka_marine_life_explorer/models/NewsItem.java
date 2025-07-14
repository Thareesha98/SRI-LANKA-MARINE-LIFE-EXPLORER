/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.students.sri_lanka_marine_life_explorer.models;

/**
 *
 * @author thareesha
 */


public class NewsItem {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String imageUrl;

    public NewsItem(String title, String description, String link, String pubDate, String imageUrl) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public String getPubDate() { return pubDate; }
    public String getImageUrl() { return imageUrl; }
}
