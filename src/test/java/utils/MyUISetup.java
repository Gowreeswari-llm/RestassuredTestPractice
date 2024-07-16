package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.URL;



import org.openqa.selenium.chrome.ChromeDriver;
import uipages.HomePage;

public class MyUISetup {
    protected static WebDriver driver;
    protected static HomePage homePage ;
    // webdriver setup for UI tests
    @BeforeClass
    public static void setup() {
        // Set the path to the chromedriver executable
        URL chromeDriverUrl = MyTestSetup.class.getClassLoader().getResource("Chromedriver/chromedriver");
        if (chromeDriverUrl == null) {
            throw new IllegalStateException("chromedriver resource not found. Ensure it's placed in the resources directory.");
        }
        String chromeDriverPath = chromeDriverUrl.getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.get("https://www.jaguarlandrover.com/");
        // Initialize HomePage object
        homePage = new HomePage(driver);
    }

    @BeforeTest
    void initHome() {
        homePage.launchPage(null);
    }
    @AfterClass
    public static void tearDown() {
        // Close the browser
        driver.quit();
    }
}
