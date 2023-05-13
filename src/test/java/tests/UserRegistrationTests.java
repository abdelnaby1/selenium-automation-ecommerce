package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
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

//    static Date date;
    String email;
    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
//        date = new Date();
//        email = "test" + date.getTime() + "@test.com";
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }

    @DataProvider(name = "userData")
    public static Object[][] userData(){
        return new Object[][]{
                {"ahmed","test", "test1" + new Date().getTime() + "@test.com","123456789"},
                {"eslam","test", "test2" + new Date().getTime() + "@test.com","1234567890"},
                {"mohamed","test", "test3" + new Date().getTime() + "@test.com","12345678900"},

        };
    }
    @Test(dataProvider = "userData")
    public void testValidRegister(String fName, String lName, String email, String password){

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
//    @Test(dependsOnMethods = {"testValidRegister"})
//    public void testLogout(){
//        new HomePage(driver)
//                .logout();
//        assertTrue(isLoginLinkDisplayed());
//
//    }
//    @Test(dependsOnMethods = {"testLogout"})
//    public void testValidLogin(){
//        new HomePage(driver)
//                .openLoginPage()
//                .login(email,"123456789");
//        assertTrue(isLogoutLinkDisplayed());
//    }

    @AfterMethod
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
