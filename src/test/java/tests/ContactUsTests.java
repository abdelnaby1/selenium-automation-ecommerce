package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactUsTests {
    Date date;
    String email;
    String name;
    String message;
    WebDriver driver;
    @BeforeClass
    public void setup()
    {
        date = new Date();
        email = "test" + date.getTime() + "@test.com";
        name = "test test";
        message = " Hello There!";
        System.out.println(email);
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }
    @Test
    public void testContactUs() {

       String successMessage =
               new HomePage(driver)
               .openContactUsPage()
               .contactUs(name,email,message)
               .getResultMessage();

       assertTrue(successMessage.contains("Your enquiry has been successfully sent to the store owner."));
    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
