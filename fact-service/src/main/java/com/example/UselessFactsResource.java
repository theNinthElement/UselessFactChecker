package com.example;

import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public class UselessFactsResource {

    private static final Logger LOG = Logger.getLogger(UselessFactsResource.class);

    // @Inject
    UselessFactService uselessFactsService = new UselessFactService();

    // Log.info("The);

    @POST
    @Path("/facts")
    @Produces(MediaType.APPLICATION_JSON)
    public UselessFactResponse fetchFact() {
        LOG.info(" Here we are fetchFact");
        return uselessFactsService.fetchRandomFact();
    }

    @GET
    @Path("/facts/{shortenedUrl}")
    @Produces(MediaType.APPLICATION_JSON)
    public CachedFact getCachedFact(@PathParam("shortenedUrl") String shortenedUrlExtension) {
        LOG.info(" Here we are getCachedFact");
        String shortenedUrl = "short.ly/"+shortenedUrlExtension;
        return uselessFactsService.getCachedFact(shortenedUrl);
    }

    @GET
    @Path("/facts")
    public List<CacheFactResponse> getAllCachedFacts() {
        LOG.info(" Here we are getAllCachedFacts");
        return uselessFactsService.getAllCachedFacts();
    }

    @GET
    @Path("/facts/{shortenedUrl}/redirect")
    public void redirectToOriginal(@PathParam("shortenedUrl") String shortenedUrlExtension) {
        LOG.info(" Here we are redirectToOriginal");
        String shortenedUrl = "short.ly/"+shortenedUrlExtension;
        uselessFactsService.incrementAccessCount(shortenedUrl);
        CachedFact fact = uselessFactsService.getCachedFact(shortenedUrl);
        if (fact != null) {
            // Redirect logic
            // return Response.seeOther(URI.create(fact.getShortendURL())).build();
        }
    }

    @GET
    @Path("/admin/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getAccessStatistics() {
        LOG.info(" Here we are getAccessStatistics");
        return uselessFactsService.getAccessStatistics();
    }    
}
