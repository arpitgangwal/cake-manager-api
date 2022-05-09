package com.waracle.cakemgr.cucumber;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Steps {
    private static final String USER_ID = "user1";
    private static final String NAME = "vanilla";
    private static final String PASSWORD = "pwd1";
    private static final String BASE_URL = "http://localhost:8080";
    private static String TOKEN;
    private static Response response;
    private static String jsonString;

    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

            response = request.body("{ \"userName\":\"" + USER_ID + "\", \"password\":\"" + PASSWORD + "\"}")
                    .post("/jwt-token");
            TOKEN = response.asString();

    }


    @When("^I add a cake to my cake list \"([^\"]*)\"$")
    public void i_add_a_cake_to_my_cake_list(String fileName) {
        RestAssured.baseURI = BASE_URL;
        response = getRequestSpecification().body(readJsonFile(fileName))
                .post("/cakes/add");
    }

    @Then("the cake is added")
    public void the_cake_is_added() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I want to list cakes")
    public void i_want_to_list_cakes() {
        RestAssured.baseURI = BASE_URL;
        response = getRequestSpecification().get("/cakes/list");
    }

    @Then("list contain one cake")
    public void list_contain_one_cake() {
        Assert.assertEquals(200, response.getStatusCode());
        jsonString = response.asString();
        List<Map<String, String>> cakes = JsonPath.from(jsonString).get();
        Assert.assertTrue(cakes.size() > 0);
        Assert.assertTrue(cakes.get(cakes.size()-1).get("name").equalsIgnoreCase(NAME) );
    }
    @Given("^I try to add cake with wrong input from file \"([^\"]*)\"$")
    public void i_try_to_add_cake_with_wrong_input(String fileName){
        RestAssured.baseURI = BASE_URL;

        response = getRequestSpecification().body(readJsonFile(fileName))
                .post("/cakes/add");
    }

    @Then("I except bad input exception")
    public void i_except_bad_input_exception(){
        Assert.assertEquals(400, response.getStatusCode());

    }

    public String readJsonFile(String fileName){
        InputStream is =
                Steps.class.getResourceAsStream( "/"+fileName);
        return IOUtils.toString( is );
    }

    public RequestSpecification getRequestSpecification(){
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + TOKEN);
        request.header("Content-Type", "application/json");
        return request;
    }

    @And("I can download file list")
    public void iCanDownloadFileList() {
        RestAssured.baseURI = BASE_URL;
        response = getRequestSpecification().get("/cakes/");
        Assert.assertNotNull(response.asByteArray());
    }

    @Given("I try to add cake with invalid format json file {string}")
    public void iTryToAddCakeWithInvalidFormatJsonFile(String fileName) {
        RestAssured.baseURI = BASE_URL;

        response = getRequestSpecification().body(readJsonFile(fileName))
                .post("/cakes/add");
    }

    @Then("I except internal server exception")
    public void iExceptInternalServerException() {
        Assert.assertEquals(500, response.getStatusCode());
    }
}
