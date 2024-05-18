package com.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommentsTest extends BaseTest {

    @Test
    public void testGetComments() {
        Response response = RestAssured.get("/comments");
        logger.info("GET /comments Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetCommentById() {
        Response response = RestAssured.get("/comments/1");
        logger.info("GET /comments/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreateComment() {
        String newComment = "{ \"name\": \"foo\", \"body\": \"bar\", \"postId\": 1, \"email\": \"test@test.com\" }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newComment)
                .post("/comments");
        logger.info("POST /comments Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "foo");
    }

    @Test
    public void testUpdateComment() {
        String updatedComment = "{ \"id\": 1, \"name\": \"foo\", \"body\": \"bar\", \"postId\": 1, \"email\": \"test@test.com\" }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updatedComment)
                .put("/comments/1");
        logger.info("PUT /comments/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "foo");
    }

    @Test
    public void testDeleteComment() {
        Response response = RestAssured.delete("/comments/1");
        logger.info("DELETE /comments/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
