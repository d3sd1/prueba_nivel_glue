package org.glue.prueba_nivel.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerE2ETest {

    @LocalServerPort
    private int port;

    @BeforeAll
    void setupRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void whenValidWeights_thenReturnsSortedProducts() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"salesWeight\":1.0,\"stockRatioWeight\":1.0}")
                .when()
                .post("/api/products/sorted")
                .then()
                .statusCode(200)
                .body("", hasSize(6))
                .body("id", contains(5, 1, 3, 2, 6, 4));
    }

    @Test
    void whenInvalidWeights_thenReturnsBadRequest() {
        // salesWeight = 0.0 is invalid as per validation
        String responseBody = given()
                .contentType(ContentType.JSON)
                .body("{\"salesWeight\":0.0,\"stockRatioWeight\":0.5}")
                .when()
                .post("/api/products/sorted")
                .then()
                .statusCode(400)
                .extract().asString();
// comentario solo para decir que soy andrei y no se porque en los commit messages sale otro nick, tengo que ajustarlo en este pc
        assertTrue(responseBody.toLowerCase().contains("bad request"));
    }
}
