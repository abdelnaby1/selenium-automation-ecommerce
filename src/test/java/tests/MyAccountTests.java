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

public class MyAccountTests {
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
    public void testChangePassword(){
        String successMsg =
                new HomePage(driver)
                        .openMyAccountPage()
                        .openChangePasswordView()
                        .changePasswrod("123456789","1234567")
                        .getSuccessMsg();
        assertEquals(successMsg,"Password was changed");
    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
