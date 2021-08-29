package com.eltestor;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPUTUpdateUser {

    @Test
    public void updateUser()
    {
        RestAssured.baseURI ="https://reqres.in";
        RestAssured.basePath="/api/users/2";

        RequestSpecification request = RestAssured.given();
        Response response = request
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"name\": \"Don\",\n" +
                        "    \"job\": \"Manager\"\n" +
                        "}")
                .put();

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8");

        String responseBodyAsString = response.getBody().asString();
        Assert.assertTrue(responseBodyAsString.contains("name"));
        Assert.assertTrue(responseBodyAsString.contains("job"));
        Assert.assertTrue(responseBodyAsString.contains("updatedAt"));

        String name = JsonPath.from(responseBodyAsString).get("name");
        Assert.assertEquals(name,"Don");
        String job = JsonPath.from(responseBodyAsString).get("job");
        Assert.assertEquals(job,"Manager");
    }
}
