package com.example;
// CachedFact.java
public class CachedFact {

    private String fact;
    private String shortendURL;
    private int accessCount;
    

    public CachedFact(String fact, String shortendURL, int accessCount) {

        this.fact = fact;
        this.shortendURL = shortendURL;
        this.accessCount = accessCount;

    }

    // Getters and Setters

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getShortendURL() {
        return shortendURL;
    }

    public void setShortendURL(String shortendURL) {
        this.shortendURL = shortendURL;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }
}