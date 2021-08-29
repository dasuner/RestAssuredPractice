package com.eltestor;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDELETEUser {
    @Test
    public void deleteUser()
    {
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api/users/2";

        RequestSpecification request = RestAssured.given();
        Response response = request.delete();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),204);
    }
}
