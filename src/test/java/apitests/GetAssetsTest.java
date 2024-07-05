package apitests;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


import io.restassured.response.Response;
import utils.MyTestSetup;


public class GetAssetsTest extends MyTestSetup {
    
    private static final Logger log = Logger.getLogger(GetAssetsTest.class);
    

    @Test(description = "Test the /sainapse/about endpoint")
    void testAboutPageInfo() {
        // Get the about page info from the API: /sainapse/about
        // Validate the response code is 200
        // Validate the response body contains the text "Sainapse"
        Response response = given()
                .header("Authorization", bearerToken)
                .when()
                .get("/sainapse/about")
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info(response.getBody().asString());

        Assert.assertTrue(response.getBody().asString().contains("Sainapse"), "Response body does not contain 'Sainapse'");

    }

    // add test for API: /sainapse/information_channel/OutlookMailIC
    // Validate the response code is 200
    // Validate the response body contains the text "result"
    @Test
    void testOutlookMailIC() {
        Response response = given()
                .header("Authorization", bearerToken)
                .when()
                .get("/sainapse/information_channel/OutlookMailIC")
                .then()
                .statusCode(200)
                .extract()
                .response();
        log.info("test finished" + response.getBody().asString());

        Assert.assertTrue(response.getBody().asString().contains("result"), "Response body does not contain 'result'");
    }



}
