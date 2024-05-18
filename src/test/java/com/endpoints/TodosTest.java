package com.endpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TodosTest extends BaseTest {

    @Test
    public void testGetTodos() {
        Response response = RestAssured.get("/todos");
        logger.info("GET /todos Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetTodoById() {
        Response response = RestAssured.get("/todos/1");
        logger.info("GET /todos/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testCreateTodo() {
        String newTodo = "{ \"title\": \"foo\", \"completed\": false, \"userId\": 1 }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newTodo)
                .post("/todos");
        logger.info("POST /todos Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testUpdateTodo() {
        String updatedTodo = "{ \"id\": 1, \"title\": \"foo\", \"completed\": false, \"userId\": 1 }";
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(updatedTodo)
                .put("/todos/1");
        logger.info("PUT /todos/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "foo");
    }

    @Test
    public void testDeleteTodo() {
        Response response = RestAssured.delete("/todos/1");
        logger.info("DELETE /todos/1 Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

