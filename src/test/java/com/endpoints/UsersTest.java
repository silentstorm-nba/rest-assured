package com.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UsersTest extends BaseTest {

    @Test
    public void testGetUsers() {
        Response response = RestAssured.get("/users");
        logger.info("GET /users Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetUserById() {
        Response response = RestAssured.get("/users/1");
        logger.info("GET /users/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreateUser() {
        String newUser = "{ \"name\": \"foo\", \"username\": \"bar\", \"email\": \"test@test.com\" }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newUser)
                .post("/users");
        logger.info("POST /users Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "foo");
    }

    @Test
    public void testUpdateUser() {
        String updatedUser = "{ \"id\": 1, \"name\": \"foo\", \"username\": \"bar\", \"email\": \"test@test.com\" }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updatedUser)
                .put("/users/1");
        logger.info("PUT /users/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "foo");
    }

    @Test
    public void testDeleteUser() {
        Response response = RestAssured.delete("/users/1");
        logger.info("DELETE /users/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

