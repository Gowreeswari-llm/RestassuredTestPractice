package apitests;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utils.LogUtils;
import utils.MyTestSetup;
import org.awaitility.Awaitility;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class PollingEndpoints extends MyTestSetup {
        private static final Logger log = LogManager.getLogger(PollingEndpoints.class);

        // Validate the data transfer status for /sainapse/train/data endpoint
        @Test
        public void testPollingEndpoint() {
            // Write your code here
            int exec_id = given()
                    .header("Authorization", bearerToken)
                    .body("[" +
                            "  {" +
                            "    \"ic_name\": \"Recommend_SharepointEDA\"," +
                            "    \"iu_names\": [" +
                            "      \"sharepoint_iu71516\"" +
                            "    ]," +
                            "    \"time_filters\": [" +
                            "      {" +
                            "        \"start_time\": \"1970-01-01T00:00:00.000Z\"," +
                            "        \"end_time\": \"2024-07-14T11:34:05.000Z\"" +
                            "      }" +
                            "    ]," +
                            "    \"type\": \"Clean\"" +
                            "  }" +
                            "]")
                    .contentType("application/json")
                    .when()
                    .post("/sainapse/train/data")
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("result[0].execution_id");
            log.info("Execution ID: " + exec_id);
            String statusEndpoint = "/sainapse/train/data/status/status?execution_id=" + exec_id;
            //poll the jobstatus endpoint: Get: /sainapse/train/data/status/status?execution_id=<execution_id>
            // Setup Awaitility to poll the endpoint every 5 seconds for 5 minutes
            Awaitility.await().atMost(5, TimeUnit.MINUTES)
                    .pollInterval(10, TimeUnit.SECONDS)
                    .until(statusCheck(statusEndpoint));
        }

    private Callable<Boolean> statusCheck(String statusEndpoint) {
        return () -> {
            LogUtils.logRequestDetails("GET", statusEndpoint, "Authorization: " + bearerToken, null);
            // Make an HTTP GET request to the status endpoint
            Response response = given()
                    .header("Authorization", bearerToken)
                    .when()
                    .get(statusEndpoint)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            String status = response.path("result[0].status");
            log.info("Current Status: " + status);

            // Return true if status is "Completed"
            return "Completed".equals(status);
        };
    }
}
