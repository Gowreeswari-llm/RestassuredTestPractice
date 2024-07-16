package utils;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import java.util.ResourceBundle;

public class MyTestSetup {

    private static final Logger log = LogManager.getLogger(MyTestSetup.class);

    public static ResourceBundle getBaseURL() {
        // add the code to read the properties file here
        ResourceBundle baseURL = ResourceBundle.getBundle("application");
        return baseURL;
        // return the properties object
    }

    @BeforeSuite
    public static void setup() {
        String base_URI = getBaseURL().getString("base_url");
        String user_name = getBaseURL().getString("username");
        String pwd = getBaseURL().getString("password");

        baseURI = base_URI;

        // add the code to login here and keep the session id to login in the other test cases
        // API: https://petstore.swagger.io/v2/user/login?username=testuser1&password=Welcome2user%40123
        // {
        //  "code": 200,
        //  "type": "unknown",
        //  "message": "logged in user session:1721035085061"
        //}
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"username\": \"" + user_name + "\",\n" +
                        "  \"password\": \"" + pwd + "\"\n" +
                        "}")
                .when()
                .post("/user/login")
                .then()
                .extract()
                .response();
        log.info(response);
    }

}