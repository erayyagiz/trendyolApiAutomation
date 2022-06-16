package com.trendyol.apiautomation.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class Endpoints {
    static final double delta = 1E-15;

    @LocalServerPort
    private Integer port;

    public String postLogin(String body) {
        RestAssured.baseURI = "http://localhost:" + port;

        Response response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .log().all()
                .request("POST", "/user")
                .then()
                .extract().response();
        System.out.println("***********************************************************\n");
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Bearer code is: " + response.getHeader("Authorization"));
        Assert.assertEquals(response.getHeader("Authorization").length(), 249);
        System.out.println("***********************************************************\n");
        return response.getHeader("Authorization");
    }

    public void add(String authToken, String params) {
        RestAssured.baseURI = "http://localhost:" + port + "/v1";
        String[] param = params.split(",");
        Response response = given()
                .queryParam("params", params)
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", authToken)
                .when()
                .log().all()
                .request("POST", "/add")
                .then()
                .extract().response();
        double total = 0;
        for (int i = 0; i < param.length; i++) {
            double paramI = Double.parseDouble(param[i]);
            total = paramI + total;
        }
        System.out.println("***********************************************************\n" + response.getBody().asString());
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        double result = Double.parseDouble(response.jsonPath().getString("result"));
        System.out.println("Expected result: " + total + " Received result: " + result);
        Assert.assertEquals(result, total,delta);
        System.out.println("***********************************************************\n");
    }

    public void subtract(String authToken, String params) {
        RestAssured.baseURI = "http://localhost:" + port + "/v1";
        String[] param = params.split(",");
        Response response = given()
                .queryParam("params", params)
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", authToken)
                .when()
                .log().all()
                .request("POST", "/subtract")
                .then()
                .extract().response();
        double total = 0;
        total = Double.parseDouble(param[0]) - Double.parseDouble(param[1]);
        System.out.println("***********************************************************\n" + response.getBody().asString());
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        double result = Double.parseDouble(response.jsonPath().getString("result"));
        System.out.println("Expected result: " + total + " Received result: " + result);
        Assert.assertEquals(result, total,delta);
        System.out.println("***********************************************************\n");
    }

    public void multiplication(String authToken, String params) {
        RestAssured.baseURI = "http://localhost:" + port + "/v1";
        String[] param = params.split(",");
        Response response = given()
                .queryParam("params", params)
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", authToken)
                .when()
                .log().all()
                .request("POST", "/multiplication")
                .then()
                .extract().response();
        double total = 1;
        for (int i = 0; i < param.length; i++) {
            double paramI = Double.parseDouble(param[i]);
            total = paramI * total;
        }
        System.out.println("***********************************************************\n" + response.getBody().asString());
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        double result = Double.parseDouble(response.jsonPath().getString("result"));
        System.out.println("Expected result: " + total + " Received result: " + result);
        Assert.assertEquals(result, total,delta);
        System.out.println("***********************************************************\n");
    }

    public void division(String authToken, String params) {
        RestAssured.baseURI = "http://localhost:" + port + "/v1";
        String[] param = params.split(",");
        Response response = given()
                .queryParam("params", params)
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", authToken)
                .when()
                .log().all()
                .request("POST", "/division")
                .then()
                .extract().response();
        double total = 0;
        total = Double.parseDouble(param[0]) / Double.parseDouble(param[1]);
        System.out.println("***********************************************************\n" + response.getBody().asString());
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        double result = Double.parseDouble(response.jsonPath().getString("result"));
        System.out.println("Expected result: " + total + " Received result: " + result);
        Assert.assertEquals(result, total,delta);
        System.out.println("***********************************************************\n");
    }

    public void sum(String authToken, String params) {
        RestAssured.baseURI = "http://localhost:" + port + "/v1";
        String[] param = params.split(",");
        Response response = given()
                .queryParam("params", params)
                .contentType("application/json")
                .accept(ContentType.JSON)
                .header("Authorization", authToken)
                .when()
                .log().all()
                .request("GET", "/sum")
                .then()
                .extract().response();
        int total=0;
        for (int i = Integer.parseInt(param[0]); i>=0; i--) {
            total = total+i;
        }
        System.out.println("***********************************************************\n" + response.getBody().asString());
        System.out.println("Status code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
        int result = Integer.parseInt(response.jsonPath().getString("result"));
        System.out.println("Expected result: " + total + " Received result: " + result);
        Assert.assertEquals(result, total);
        System.out.println("***********************************************************\n");
    }
}
