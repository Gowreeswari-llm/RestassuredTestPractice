package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    private static final Logger log = LogManager.getLogger(LogUtils.class);

    public static void logRequestDetails(String method, String url, String headers, String body) {
        log.info("Request Method: " + method);
        log.info("Request URL: " + url);
        log.info("Request Headers: " + headers);
        if (body != null && !body.isEmpty()) {
            log.info("Request Body: " + body);
        }
    }
}