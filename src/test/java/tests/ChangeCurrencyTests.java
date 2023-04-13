package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.SearchResultPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ChangeCurrencyTests {
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);


    }
    @Test
    public void testSearchByAutoSuggestions() {
        new HomePage(driver)
                .changeCurrency("Euro");

        String keyword = "mac";
        String productTitle =
                new SearchPage(driver)
                        .searchByKeyword(keyword)
                        .getProductTitle();

        assertTrue(productTitle.toLowerCase().contains(keyword.toLowerCase()));

        String price =
                new ProductDetailsPage(driver)
                .getPrice();
        assertTrue(price.contains("€"));
    }

    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
