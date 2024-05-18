package com.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlbumsTest extends BaseTest {

    @Test
    public void testGetAlbums() {
        Response response = RestAssured.get("/albums");
        logger.info("GET /albums Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetAlbumById() {
        Response response = RestAssured.get("/albums/1");
        logger.info("GET /albums/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreateAlbum() {
        String newAlbum = "{ \"title\": \"foo\", \"userId\": 1 }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newAlbum)
                .post("/albums");
        logger.info("POST /albums Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testUpdateAlbum() {
        String updatedAlbum = "{ \"id\": 1, \"title\": \"foo\", \"userId\": 1 }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updatedAlbum)
                .put("/albums/1");
        logger.info("PUT /albums/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testDeleteAlbum() {
        Response response = RestAssured.delete("/albums/1");
        logger.info("DELETE /albums/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

