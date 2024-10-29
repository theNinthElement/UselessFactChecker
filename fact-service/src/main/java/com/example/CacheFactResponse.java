package com.example;

/*
 * Class describes a response to Fact API call
 */
public class CacheFactResponse {

    private String fact;
    private String original_URL;

    public String getFact() {
        return fact;
    }

    public void setfact(String fact) {
        this.fact = fact;
    }

    public String getOriginalURL() {
        return original_URL;
    }

    public void setOriginalURL(String originalURL) {
        this.original_URL = originalURL;
    }

}