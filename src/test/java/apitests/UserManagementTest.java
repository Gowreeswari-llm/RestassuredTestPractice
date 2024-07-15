package apitests;

import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LogUtils;
import utils.MyTestSetup;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserManagementTest extends MyTestSetup {
    private static final Logger log = LogManager.getLogger(UserManagementTest.class);
        // Add a test method to test the API: /sainapse/user_management/users
        // Validate the response code is 200
        // Validate the response body contains the text "users"
        // Use the logRequestDetails method from the LogUtils class to log the request details
        // Use the log.info method to log the response body
        // Use the Assert class to validate the response body contains the text "users"
    @Test(description = "Create Users Test")
    public void testCreateUsers() {
        // Inside your test method
        LogUtils.logRequestDetails("POST", "/sainapse/sign_up", "Authorization: " + bearerToken, null);
        try {
        Response response = given()
                .header("Authorization", bearerToken)
                .body("{" +
                        "  \"auth_type\": \"\"," +
                        "  \"description\": \"User creating using automation\"," +
                        "  \"email\": \"testuser2@sainapse.ai\"," +
                        "  \"groups\": [" +
                        "    " +
                        "  ]," +
                        "  \"name\": \"testuser2\"," +
                        "  \"password\": \"Welcome2testuser@123\"" +
                        "}")
                .contentType("application/json")
                .when()
                .post("/sainapse/sign_up")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonschemas/createUserResp.json"))
                .extract()
                .response();

        log.info(response.getBody().asString());

        Assert.assertTrue(response.getBody().asString().contains("users"), "Response body does not contain 'users'");
        } catch (Exception e) {
            log.error(" API failed with Error: {}", e.getMessage());
        }
    }

    // Chained API calls: Get user details and get the role of the user
    @Test(description = "Get Users Test")
    public void testGetUsers() {
        String emai_id = "testuser2@sainapse.ai";
        // Inside your test method
        LogUtils.logRequestDetails("GET", "/sainapse/user/{email}", "Authorization: " + bearerToken, null);

        String grpName = given()
                .header("Authorization", bearerToken)
                .pathParam("email", emai_id)
                .when()
                .get("/sainapse/user/{email}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("result.groups[0].name");
        log.info("Group Name: {}", grpName);
        LogUtils.logRequestDetails("GET", "/sainapse/role/group/{group_name}", "Authorization: " + bearerToken, null);
        // get the role of the user
        String roleName = given()
                .header("Authorization", bearerToken)
                .pathParam("group_name", grpName)
                .when()
                .get("/sainapse/role/group/{group_name}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("result[0].name");
        Assert.assertEquals(roleName, "SUPER_ADMIN", "The role name is not SUPER_ADMIN");

//        log.info("API Response{}", response.getBody().asString());
//
//        Assert.assertTrue(response.getBody().asString().contains("result"), "Response body does not contain 'result'");
//        Assert.assertTrue(response.getBody().jsonPath().get("result.email").equals(emai_id), "Response body does not contain 'result'");
    }

    @Test(description = "Delete Users Test")
    public void testDeleteUsers() {
        String emai_id = "testuser2@sainapse.ai";
        // Inside your test method
        LogUtils.logRequestDetails("DELETE", "/sainapse/user/{email}", "Authorization: " + bearerToken, null);
        // Delete the user
        Response response = given()
                .header("Authorization", bearerToken)
                .pathParam("email", emai_id)
                .when()
                .delete("/sainapse/user/{email}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

}
