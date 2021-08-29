package com.eltestor;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGETInventory {

    @Test
    public void getInventory()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        RestAssured.basePath ="/v2/store/inventory";

        RequestSpecification request = RestAssured.given();
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getHeader("content-type"),"application/json");

        String responseBodyString = response.getBody().asString();
        Assert.assertTrue(responseBodyString.contains("avaliable"));

        int availableValue = JsonPath.from(responseBodyString).get("avaliable");
        System.out.println("Available is: "+availableValue);
    }
}
