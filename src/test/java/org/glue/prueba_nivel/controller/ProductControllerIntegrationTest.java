package org.glue.prueba_nivel.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * NOTA:
 * CON ESTE TEST SE DEBERIA PODER VALIDAR TODO.
 * HE AGREGADO TAMBIÉN TESTS UNITARIOS PARA HACER UN "POCO DE CADA". PERO LO SUYO SERÍA PONER TODAS LAS LÓGICAS AQUÍ EN UN PROYECTO REAL.
 * AUNQUE PUEDE COEXISTIR CON UNITARIOS :)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    static void setupRestAssured(@LocalServerPort int port) {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void givenBalancedWeights_whenGetSortedProducts_thenReturnsSortedList() {
        String requestJson = "{\"salesWeight\": 0.5, \"stockRatioWeight\": 0.5}";
        given().contentType("application/json").body(requestJson)
                .when().post("/api/products/sorted")
                .then().statusCode(200)
                .body("size()", equalTo(6));
    }

    @Test
    void givenZeroWeight_whenPostSorted_thenBadRequest() {
        String requestJson = "{\"salesWeight\": 0.0, \"stockRatioWeight\": 0.5}";
        given().contentType("application/json").body(requestJson)
                .when().post("/api/products/sorted")
                .then().statusCode(400);
    }

    @Test
    void givenOverweight_whenPostSorted_thenBadRequest() {
        String requestJson = "{\"salesWeight\": 1.5, \"stockRatioWeight\": 0.5}";
        given().contentType("application/json").body(requestJson)
                .when().post("/api/products/sorted")
                .then().statusCode(400);
    }

    @Test
    void givenMissingSalesWeight_whenPostSorted_thenBadRequest() {
        String requestJson = "{\"stockRatioWeight\": 0.5}";
        given().contentType("application/json").body(requestJson)
                .when().post("/api/products/sorted")
                .then().statusCode(400);
    }

    @Test
    void givenMissingStockWeight_whenPostSorted_thenBadRequest() {
        String requestJson = "{\"salesWeight\": 0.5}";
        given().contentType("application/json").body(requestJson)
                .when().post("/api/products/sorted")
                .then().statusCode(400);
    }

    @Test
    void givenSalesDominant_whenSorted_thenSalesInfluenceIsHigh() {
        String requestJson = "{\"salesWeight\": 1.0, \"stockRatioWeight\": 0.1}";
        Response response = given().contentType("application/json").body(requestJson)
                .when().post("/api/products/sorted");
        response.then().statusCode(200);
        List<String> ids = response.jsonPath().getList("id");
        assertEquals(5, ids.get(0)); // Highest sales
    }

}
