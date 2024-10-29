package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.logging.Log;


public class UselessFactResponse {

    
    private String originalFact;
    private String shortenedUrl;

    URLShortner urlshortner = new URLShortner();

    public UselessFactResponse(String response) {

        try {
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            //Accessing the Fields 
            this.originalFact = jsonNode.get("text").asText();
            String originalURL = jsonNode.get("permalink").asText();
            this.shortenedUrl = urlshortner.shortenUrl(originalURL);

        } catch (Exception e) {
            Log.info("Failed to fetch the from the JSON reader" + e.getMessage());
        }

    }

     // Getters and Setters
     public String getFact() {
        return originalFact;
    }

    public void setFact(String originalFact) {
        this.originalFact = originalFact;
    }

    public String getShortendURL() {
        return shortenedUrl;
    }

    public void setShortendURL(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }
}
