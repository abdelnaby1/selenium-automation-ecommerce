package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.Helper.extractOrderNumber;

public class CheckoutTests {
    Date date;
    String email;
    WebDriver driver;

    @BeforeClass
    public void setup()
    {
        date = new Date();
        email = "test" + date.getTime() + "@test.com";

        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

        new HomePage(driver)
                .openRegistrationPage()
                .register("ahmed","test",email,"123456789")
                .clickContinueBtn()
                .openLoginPage()
                .login(email,"123456789");

    }
    @Test()
    public void testCheckout(){
        String product = "Apple MacBook Pro 13-inch";
        new SearchPage(driver)
                .searchByProductName(product)
                .openProductDetails(1)
                .addProductToCart();

        var title =
            new HomePage(driver)
                .openCart()
                .checkout()
                .fillTheAddressDetails("Egypt","Cairo","Cairo, Maadi","3433","01099983636")
                .chooseDefaultShippingMethod()
                .chooseDefaultPaymentMethod()
                .continueOnPaymentInfo()
                .confirmCheckout()
                .getTitle();
        assertEquals(title,"Your order has been successfully processed!","thr order should be created");

    }

    @Test(dependsOnMethods = "testCheckout")
    public void testOrderDetails(){
        String orderNumberText =
                new CheckoutPage(driver)
                        .getOrderNumber();
        String orderNumber = extractOrderNumber(orderNumberText);
        new CheckoutPage(driver).openOrderDetails();
        assertTrue(driver.getCurrentUrl().contains("orderdetails/"+orderNumber));
        new OrderDetailsPage(driver).downloadPDFOrderInvoice();

    }


    @AfterClass
    public void closeBrowser() {
//        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
