package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.RegisterResultPage;
import pages.SearchPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.HomePage.isLoginLinkDisplayed;
import static pages.HomePage.isLogoutLinkDisplayed;

public class EmailAFriendTests {
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

        new SearchPage(driver)
                .searchByKeyword("laptop");
    }



    @Test
    public void testEmailAFriend(){
       String message =
               new ProductDetailsPage(driver)
                .clickEmailAFriend()
                .emailYourFriend("ahme@test.com","Amazing Product")
                       .getMessage();

        assertEquals(message,"Your message has been sent.");
    }

    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
