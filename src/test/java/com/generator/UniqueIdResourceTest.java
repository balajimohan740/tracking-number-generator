package com.generator;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

@QuarkusTest
class UniqueIdResourceTest {

    @Test
    void testUniqueIdEndpoint() {
        given()
                .when().get("/unique-id")
                .then()
                .statusCode(200)
                .body(matchesPattern("^[0-9]+$"));
    }
}
