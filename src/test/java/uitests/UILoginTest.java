package uitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.MyTestSetup;

import java.net.URL;
import java.time.Duration;

public class UILoginTest {
    private static final Logger log = LogManager.getLogger(UILoginTest.class);
    // Setup the webdriver
    // Navigate to the login page
    // Enter the username
    // Enter the password
    // Click the login button
    // Verify the login is successful
    private static  WebDriver driver ;

    @BeforeClass
    public static void setup() {
        URL chromeDriverUrl = UILoginTest.class.getClassLoader().getResource("Chromedriver/chromedriver");
        if (chromeDriverUrl == null) {
            throw new IllegalStateException("chromedriver resource not found. Ensure it's placed in the resources directory.");
        }
        String chromeDriverPath = chromeDriverUrl.getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.get("https://testing.bayestree.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // //img[@alt='logo']
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='logo']")));
    }

    @Test
    public void testLogin() throws InterruptedException {
        // Enter the username
        // Enter the password
        // Click the login button
        // Verify the login is successful
        log.info("login page test");
        //Thread.sleep(10000);

    }

    @AfterClass
    public static void tearDown() {
        // Close the browser
        driver.quit();
    }
}
