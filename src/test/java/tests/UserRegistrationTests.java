package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterResultPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.HomePage.isLoginLinkDisplayed;
import static pages.HomePage.isLogoutLinkDisplayed;
//mvn test -Dtest=UserRegistrationTests
//mvn test -Dtest=Test1#methodname // for a snigle method

@Epic("Ecommerce")
@Feature("User Registration")
public class UserRegistrationTests {

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

    }


    @Test
    public void testValidRegister(){

        String successMsg =
                new HomePage(driver)
                .openRegistrationPage()
                .register("ahmed","test",email,"123456789")
                        .getSuccessMessage();
        assertTrue(successMsg.contains("Your registration completed"));

        new RegisterResultPage(driver)
                .clickContinueBtn()
                .openLoginPage()
                .login(email,"123456789");

    }
    @Test(dependsOnMethods = {"testValidRegister"})
    public void testLogout(){
        new HomePage(driver)
                .logout();
        assertTrue(isLoginLinkDisplayed());

    }
    @Test(dependsOnMethods = {"testLogout"})
    public void testValidLogin(){
        new HomePage(driver)
                .openLoginPage()
                .login(email,"123456789");
        assertTrue(isLogoutLinkDisplayed());
    }

    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
