package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WishlistTests {
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);


    }
    @Test
    public void testAddToWishlist() {
        WishlistPage wishlistPage
                = new SearchPage(driver)
                .searchByKeyword("laptop")
                .addProductToWishlist()
                .openWishlist();
        assertEquals(wishlistPage.getProductName(),wishlistPage.getRecentlyAddedProduct());

    }
    @Test(dependsOnMethods = {"testAddToWishlist"})
    public void testRemoveFromWishlist() {
        String message =
                new WishlistPage(driver)
                .removeRecentlyAddedProduct()
                .getMessageAfterDeleting();
        assertEquals(message, "The wishlist is empty!");

    }


    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
