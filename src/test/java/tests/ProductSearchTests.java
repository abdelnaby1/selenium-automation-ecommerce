package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import pages.SearchResultPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductSearchTests {
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }
    @Test
    public void testSearchByValidProduct(){
        String productName = "Apple MacBook Pro 13-inch";
        String searchedKeyword =
                new SearchPage(driver)
                .searchByProductName(productName)
                .getSearchedKeyword();

        assertEquals(searchedKeyword,productName);

        String productTitle =
                new SearchResultPage(driver)
                .openProductDetails(1)
                        .getProductTitle();
        assertEquals(productTitle,productName);
    }
    @Test
    public void testSearchByAutoSuggestions(){
        String keyword = "mac";
        String productTitle =
                new SearchPage(driver)
                        .searchByKeyword(keyword)
                                .getProductTitle();

        assertTrue(productTitle.toLowerCase().contains(keyword.toLowerCase()));
    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
