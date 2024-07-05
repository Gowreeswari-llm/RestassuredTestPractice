package utils;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MyTestSetup {

    protected static String bearerToken;


    @BeforeClass
    public static void setup() {

        baseURI = "https://testing.bayestree.com";
        // add the code to login here and keep the bearer token to login in the other test cases
        // payload for login: { "auth_type": "", "email": "admin@sainapse.ai", "password": "Admin@123"}
        // API: /sainapse/sign_in
        // Store the bearer token in a static variable to use in other test cases
        String loginPayload = "{ \"auth_type\": \"\", \"email\": \"admin@sainapse.ai\", \"password\": \"Admin@123\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .post("/sainapse/sign_in")
                .then()
                .statusCode(200)
                .extract()
                .response();

        bearerToken = response.getHeader("Authorization");
        System.out.println("Bearer token: " + bearerToken);

    }

}