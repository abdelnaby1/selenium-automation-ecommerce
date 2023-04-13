package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import static org.testng.Assert.assertTrue;

public class HoverMenuTests {
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);


    }
    @Test
    public void testHoverMenu() {
        String category = "Computers";
        String subcategory = "Notebooks";

        String subcategoryName =
                new HomePage(driver)
                .clickOnMenu(category,subcategory)
                .getSubcategoryName();

        assertTrue(driver.getCurrentUrl().contains(subcategoryName.toLowerCase()));
    }


    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
