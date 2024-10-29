package com.example;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.logging.Log;

public class URLShortner {
    private final static Map<String, String> urlMap = new HashMap<>();
    private final String baseUrl = "short.ly/";

    // Method to fetch the shortened URL
    public String shortenUrl(String originalUrl) {
        int hashCode = Math.abs(originalUrl.hashCode()); // Get absolute value
        String shortUrl = baseUrl + hashCode; // Create short URL
        urlMap.put(shortUrl, originalUrl); // Store in the map
        Log.info("Adding Short URL is : "+ shortUrl + " and the original links is : " + urlMap.get(shortUrl));
        return shortUrl;
    }

    // Method to fetch the original URL
    public String getOriginalUrl(String shortUrl) {
        Log.info("Retrieveing Short URL is : "+ shortUrl + " and the original links is : " + urlMap.get(shortUrl));
        return urlMap.get(shortUrl); // Retrieve from the map
    }    
}
