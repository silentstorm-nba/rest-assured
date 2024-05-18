package com.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostsTest extends BaseTest {

    @Test
    public void testGetPosts() {
        Response response = RestAssured.get("/posts");
        logger.info("GET /posts Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetPostById() {
        Response response = RestAssured.get("/posts/1");
        logger.info("GET /posts/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreatePost() {
        String newPost = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newPost)
                .post("/posts");
        logger.info("POST /posts Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testUpdatePost() {
        String updatedPost = "{ \"id\": 1, \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updatedPost)
                .put("/posts/1");
        logger.info("PUT /posts/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testDeletePost() {
        Response response = RestAssured.delete("/posts/1");
        logger.info("DELETE /posts/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
