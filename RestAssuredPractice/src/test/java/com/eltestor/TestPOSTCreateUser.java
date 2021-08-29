package com.eltestor;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPOSTCreateUser {

    @Test
    public void createUser()
    {
        RestAssured.baseURI ="https://reqres.in";
        RestAssured.basePath="/api/users";

        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type","application/json")
                .body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}")
                .post();

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8");
        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("name"));
        Assert.assertTrue(responseBodyAsString.contains("job"));
        Assert.assertTrue(responseBodyAsString.contains("id"));
        Assert.assertTrue(responseBodyAsString.contains("createdAt"));

        String name = JsonPath.from(responseBodyAsString).get("name");
        Assert.assertEquals(name,"morpheus");
        String job = JsonPath.from(responseBodyAsString).get("job");
        Assert.assertEquals(job,"leader");
        String id = JsonPath.from(responseBodyAsString).get("id");
        System.out.println("The ID is "+id);
    }
}
