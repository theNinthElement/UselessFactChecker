package com.example;

import java.net.URI;
import java.util.List;

import org.jboss.logging.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
public class UselessFactsResource {

    private static final Logger LOG = Logger.getLogger(UselessFactsResource.class);

    // @Inject
    UselessFactService uselessFactsService = new UselessFactService();

    /*
     * The main Post method that takes the '/fact' and calls the API 
     */
    @POST
    @Path("/facts")
    @Produces(MediaType.APPLICATION_JSON)
    public UselessFactResponse fetchFact() {
        LOG.info(" Here we are fetchFact");
        return uselessFactsService.fetchRandomFact();
    }

    /*
     * GetCachedFact method tries to find the target shortned URL code when only the URL is send 
     * for example : https://localhost:8080/facts/63463783
     */
    @GET
    @Path("/facts/{shortenedUrl}")
    @Produces(MediaType.APPLICATION_JSON)
    public CachedFact getCachedFact(@PathParam("shortenedUrl") String shortenedUrlExtension) {
        LOG.info(" Here we are getCachedFact");
        String shortenedUrl = "short.ly/"+shortenedUrlExtension;

        CachedFact fact = uselessFactsService.getCachedFact(shortenedUrl, 0);
        if (fact!=null) {
            return uselessFactsService.getCachedFact(shortenedUrl, 1);
        } else {
            return null;
        }        
    }

    /*
     * Send backs all the cached facts 
     */
    @GET
    @Path("/facts")
    public List<CacheFactResponse> getAllCachedFacts() {
        LOG.info(" Here we are getAllCachedFacts");
        return uselessFactsService.getAllCachedFacts();
    }


    /*
     * redirect to Original redirects the shornented URL to the Original URL
     * for example : https://localhost:8080/facts/63463783/redirect
     */
    @GET
    @Path("/facts/{shortenedUrl}/redirect")
    public Response redirectToOriginal(@PathParam("shortenedUrl") String shortenedUrlExtension) {
        LOG.info(" Here we are redirectToOriginal");
        String shortenedUrl = "short.ly/"+shortenedUrlExtension;
        uselessFactsService.incrementAccessCount(shortenedUrl);
        URLShortner urlShortner = new URLShortner();
        CachedFact fact = uselessFactsService.getCachedFact(shortenedUrl, 1);
        if (fact != null) {
            // Redirect logic
            return Response.temporaryRedirect(
                URI.create(urlShortner.getOriginalUrl(shortenedUrl))).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("False Short URL provided")
                           .build();
        }
    }

    /*
     * This Method sends a series of statistics back based on the accesscount and shortned URL
     */
    @GET
    @Path("/admin/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccessStatistics() {
        LOG.info(" Here we are getAccessStatistics");
        return uselessFactsService.getAccessStatistics();
    }    
}
