package uitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import utils.MyUISetup;

public class UITest extends MyUISetup
{
    private static final Logger log = LogManager.getLogger(UITest.class);

    @Test
    public void testLogo() {
        // Locator for the logo
        homePage.getLogo().isDisplayed();
        log.info("Test ran successfully");
    }
    @Test
    public void testBrands() {
        // Locator for the brands
        homePage.getBrandNames().forEach(brand -> log.info(brand));
        log.info("Test ran successfully");
    }


}
