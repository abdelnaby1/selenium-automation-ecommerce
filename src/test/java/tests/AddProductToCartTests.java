package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;

public class AddProductToCartTests {
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }
    @Test()
    public void testAddProductToCart(){
        String product = "Apple MacBook Pro 13-inch";
        String toastMsg =
                new SearchPage(driver)
                .searchByProductName(product)
                        .openProductDetails(1)
                        .addProductToCart()
                                .getToastMessage();
        assertEquals(toastMsg,"The product has been added to your shopping cart");

        String productName =
                new HomePage(driver)
                .openCart()
                        .getProductName();
        assertEquals(productName,product);
    }
    @Test(dependsOnMethods = "testAddProductToCart")
    public void testIncreaseQty(){
       String qty = "5";
       String newQty =
               new CartPage(driver)
               .updateQty(qty)
               .getQtyInput();
       assertEquals(newQty,qty);
    }

    @Test(dependsOnMethods = "testIncreaseQty")
    public void testRemoveProductFromCart(){
       String text =
               new CartPage(driver)
               .removeprodcut()
                       .getNoDataText();
        assertEquals(text,"Your Shopping Cart is empty!");
    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
