package apitests;

import api.endpoints.PetEndpoints;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.restassured.response.Response;
import utils.MyTestSetup;
import java.io.File;
import java.net.URL;



public class PetTest extends MyTestSetup {

    private static final Logger log = LogManager.getLogger(PetTest.class);

    // Get pets by status available
    // API: https://petstore.swagger.io/v2/pet/findByStatus?status=available
    // Validate the response code is 200
    // Validate the response should have more than 1 pet
    @Test(dataProvider = "petStatus", dataProviderClass = utils.DataProviders.class)
    public void testGetAssets(String petStatus) {
        // call the getPetByStatus function from PetEndpoints class
        // validate the response code is 200
        Response response = PetEndpoints.getPetByStatus(petStatus);
        Assert.assertNotNull(response, "Response is null for status: " + petStatus);
        log.info(response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        // validate the responce contains the more than 1 objects
        Assert.assertTrue(response.jsonPath().getList("$").size() > 1);
    }

    // get pet by id
    @Test(dataProvider = "petId", dataProviderClass = utils.DataProviders.class)
    public void testGetPetById(int petId  ) {
        // call the getPetByID function from PetEndpoints class
        // validate the response code is 200
        // validate the pet id from the response
        // validate the status of the pet is available
        Response response = PetEndpoints.getPetByID(petId);
        Assert.assertNotNull(response, "Response is null for petId: " + petId);
        log.info(response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), petId );
        Assert.assertEquals(response.jsonPath().getString("status"), "available");

    }

    //Create a new pet in the store
    @Test
    public void testCreatePet () {
        String payload = "{" +
                "  \"name\": \"mypet11\"," +
                "  \"photoUrls\": [" +
                "    \"\"" +
                "  ]," +
                "  \"tags\": [" +
                "    {" +
                "      \"id\": 0," +
                "      \"name\": \"\"" +
                "    }" +
                "  ]," +
                "  \"status\": \"available\"" +
                "}";

        // call the createPet function from PetEndpoints class
        // validate the response code is 200
        // validate the pet id from the response
        Response response = PetEndpoints.createPet(payload);
        log.info(response.body().prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200);
        //validate the petid from responce
        log.info(response.jsonPath().getInt("id"));
        // get the pet id from the response
        int petId = response.jsonPath().getInt("id");
        // Upload an image for a pet id
        // API: https://petstore.swagger.io/v2/pet/{petId}/uploadImage
        // Validate the response  {
        //  "code": 200,
        //  "type": "unknown",
        //  "message": "additionalMetadata: nullFile uploaded to ./2024-07-15_15-09.png, 200416 bytes"
        //}

        URL imageUrl = this.getClass().getResource("/petimages/2024-07-15_15-09.png");
        File imageFile = new File(imageUrl.getPath());
        response = PetEndpoints.uploadImage(petId, imageFile.getAbsolutePath());
        log.info(response.body().prettyPrint());
        Assert.assertEquals(response.jsonPath().getInt("code"), 200);
        // get the pet by id and check photoUrls is not null
        response = PetEndpoints.getPetByID(petId);
        log.info(response.body().prettyPrint());
        log.info(response.jsonPath().getString("photoUrls[0]"));
        Assert.assertNotNull(response.jsonPath().getString("photoUrls[0]"));
    }

}
