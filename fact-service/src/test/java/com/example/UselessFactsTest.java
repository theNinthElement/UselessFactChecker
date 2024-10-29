package com.example;

import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
class UselessFactsTest {
    @Test
    public void testUselessPostFactEndpoint() {
        given()
          .when()
            .post("/facts")
        .then()
             .statusCode(200)
             .body("fact", notNullValue());
    }

    @Test
    public void testFailFetchFactEndpoint() {
        given()
          .when()
            .get("/facts")
        .then()
             .statusCode(204);
    }

    @Test
    public void testPostAndGetFactEndpoint() {
      given()
        .when()
          .post("/facts")
        .then()
          .statusCode(200);

        given()
          .when()
            .get("/facts")
          .then()
            .statusCode(200)
              .body("fact", notNullValue());

    }

    @Test
    public void testgetAccessStatistics() {
      given()
        .when()
          .post("/facts")
        .then()
          .statusCode(200);

      given()
        .when()
          .post("/facts")
        .then()
          .statusCode(200);

       given()
          .when()
            .get("/admin/statistics")
          .then()
            .statusCode(200)
              .body("shortenedUrl", notNullValue());

        // Log.info("Here is the response " + r.jsonPath());

    }

}