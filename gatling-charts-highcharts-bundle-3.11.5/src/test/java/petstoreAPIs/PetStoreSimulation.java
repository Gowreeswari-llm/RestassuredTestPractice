package petstoreAPIs;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class PetStoreSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://petstore.swagger.io/v2") // Base URL
            .acceptHeader("application/json"); // Common header

    ScenarioBuilder scn = scenario("Fetch Pet Details")
            .exec(http("GetPetById")
                    .get("/pet/1") // Assuming pet ID 1 for demonstration
                    .check(status().is(200))); // Check for 200 OK response

    {
        setUp(scn.injectOpen(rampUsers(10).during(20))).protocols(httpProtocol); // Run scenario with 1 user
    }
}