package com.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhotosTest extends BaseTest {

    @Test
    public void testGetPhotos() {
        Response response = RestAssured.get("/photos");
        logger.info("GET /photos Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetPhotoById() {
        Response response = RestAssured.get("/photos/1");
        logger.info("GET /photos/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreatePhoto() {
        String newPhoto = "{ \"title\": \"foo\", \"albumId\": 1, \"url\": \"http://test.com\", \"thumbnailUrl\": \"http://test.com\" }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newPhoto)
                .post("/photos");
        logger.info("POST /photos Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testUpdatePhoto() {
        String updatedPhoto = "{ \"id\": 1, \"title\": \"foo\", \"albumId\": 1, \"url\": \"http://test.com\", \"thumbnailUrl\": \"http://test.com\" }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updatedPhoto)
                .put("/photos/1");
        logger.info("PUT /photos/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testDeletePhoto() {
        Response response = RestAssured.delete("/photos/1");
        logger.info("DELETE /photos/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

