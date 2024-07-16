package api.endpoints;

import apitests.PetTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.LogUtils;

import java.io.File;

import static io.restassured.RestAssured.*;

public class PetEndpoints {
    private static final Logger log = LogManager.getLogger(PetTest.class);
    // this class will have functions to retrieve the endpoints for the pet API
    // for example, getPetByID, getPetByStatus, etc.
    // the functions will return the response object
    // the functions will be called from the test classes
    // the functions will be static so they can be called without creating an object of the class

    public static Response getPetByID(int petId) {
        log.info("Getting pet by ID: " + petId);
        LogUtils.logRequestDetails("GET", Routes.PET_ID, null, null);
        Response response = given()
                .when()
                .get(Routes.PET_ID, petId)
                .then()
                .extract()
                .response();
        return response;
    }

    public static Response getPetByStatus(String status) {
        log.info("Getting pet by status: " + status);
        LogUtils.logRequestDetails("GET", Routes.PET_STATUS, null, null);
        Response response = given()
                .pathParam("status", status)
                .when()
                .get(Routes.PET_STATUS)
                .then()
                .extract()
                .response();
        return response;
    }

    public static Response createPet(String payload) {
        log.info("Creating a new pet in the store");
        LogUtils.logRequestDetails("POST", Routes.PET, "application/json", payload);
        Response response = given()
                .body(payload)
                .contentType("application/json")
                .when()
                .post(Routes.PET)
                .then()
                .extract()
                .response();
        return response;
    }

    // upload image for the given petid
    public static Response uploadImage(int petId, String filePath) {
        log.info("Uploading image for pet id: " + petId);
        Response response = given()
                .multiPart(new File(filePath))
                .when()
                .post(Routes.PET_UPLOAD_IMAGE, petId)
                .then()
                .extract()
                .response();
        return response;
    }
}
