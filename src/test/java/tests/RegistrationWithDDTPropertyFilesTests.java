package tests;

import data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterResultPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertTrue;
import static pages.HomePage.isLoginLinkDisplayed;

public class RegistrationWithDDTPropertyFilesTests {
    WebDriver driver;
    String fName = LoadProperties.userData.getProperty("fName");
    String lName = LoadProperties.userData.getProperty("lName");
    String email = LoadProperties.userData.getProperty("email");
    String password = LoadProperties.userData.getProperty("password");

    @BeforeMethod
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }


    @Test
    public void testValidRegister(){

        String successMsg =
                new HomePage(driver)
                        .openRegistrationPage()
                        .register(fName,lName,email,password)
                        .getSuccessMessage();
        assertTrue(successMsg.contains("Your registration completed"));

        new RegisterResultPage(driver)
                .clickContinueBtn()
                .openLoginPage()
                .login(email,password);

        new HomePage(driver)
                .logout();
        assertTrue(isLoginLinkDisplayed());

    }

    @AfterMethod
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
