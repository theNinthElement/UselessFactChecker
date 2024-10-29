package com.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import io.quarkus.logging.Log;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class UselessFactService {    
    // private final String API_URL = "https://uselessfacts.jsph.pl/random.json?language=en"; // TODO Add the re-direct logic
    private final String API_URL = "https://uselessfacts.jsph.pl/api/v2/facts/random?language=en";


    private final ConcurrentHashMap<String, CachedFact> cache = new ConcurrentHashMap<>();
    private final Client client = ClientBuilder.newClient();

    public UselessFactResponse fetchRandomFact() {
        Response response = client.target(API_URL)
            .request(MediaType.APPLICATION_JSON)
            .get();           

        Log.info("The response to the random generator is : " + response.getStatus());

        if (response.getStatus() == 200) {
            // Log.info("The response to the random generator is : " + response.readEntity(String.class));
            UselessFactResponse factResponse = new UselessFactResponse(response.readEntity(String.class));
            CachedFact cachedFact = new CachedFact(factResponse.getFact(), factResponse.getShortendURL(), 0);
            cache.put(factResponse.getShortendURL(), cachedFact);
            return factResponse;
        } else {
            throw new RuntimeException("Failed to fetch fact");
        }
    }

    
    public CachedFact getCachedFact(String shortenedUrl, int increase) {
        
        if(cache.containsKey(shortenedUrl) && increase == 1) 
        {
            incrementAccessCount(shortenedUrl);    
        }

        return cache.get(shortenedUrl);
    }

    public void incrementAccessCount(String shortenedUrl) {
        CachedFact fact = cache.get(shortenedUrl);
        if (fact != null) {
            Log.info("Increasing the acceess Count before : " + fact.getAccessCount());
            fact.setAccessCount(fact.getAccessCount() + 1);
            Log.info("Increasing the acceess Count before : " + fact.getAccessCount());
        }
    }

    public List<CacheFactResponse> getAllCachedFacts() {
        
        List<CacheFactResponse> list = new ArrayList<>();
        URLShortner urlshortner = new URLShortner();
        
        if (!cache.isEmpty()) {
            
            for (Map.Entry<String, CachedFact> entry : cache.entrySet()) {
                CachedFact fact = entry.getValue();
                CacheFactResponse cfr = new CacheFactResponse();
                cfr.setfact(fact.getFact());
                cfr.setOriginalURL(urlshortner.getOriginalUrl(fact.getShortendURL()));
                list.add(cfr);
            }          
            return list;

            // return new ArrayList<>(cache.values());
        } else {
            Log.info(" Return triggered on an non empty cache ");
            return null;
        }        
    }

    public Map<String, Integer> getAccessStatistics() {
        return cache.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getAccessCount()));
    }
}
