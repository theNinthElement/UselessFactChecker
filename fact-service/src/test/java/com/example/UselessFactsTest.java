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
    public void testFetchFactEndpoint() {
        given()
          .when()
            .get("/facts")
        .then()
             .statusCode(200)
             .body("fact", notNullValue());
    }

}