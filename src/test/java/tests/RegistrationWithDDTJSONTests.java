package tests;

import data.JSONReader;
import data.LoadProperties;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterResultPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.io.IOException;

import static org.testng.Assert.assertTrue;
import static pages.HomePage.isLoginLinkDisplayed;

public class RegistrationWithDDTJSONTests {
    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }


    @Test
    public void testValidRegister() throws IOException, ParseException {
        String filepath = System.getProperty("user.dir")+"/src/test/resources/testData/userData.json";
        JSONReader jsonReader = new JSONReader(filepath);

        String successMsg =
                new HomePage(driver)
                        .openRegistrationPage()
                        .register(jsonReader.getfName(),jsonReader.getlName(),jsonReader.getEmail(),jsonReader.getPassword())
                        .getSuccessMessage();
        assertTrue(successMsg.contains("Your registration completed"));

        new RegisterResultPage(driver)
                .clickContinueBtn()
                .openLoginPage()
                .login(jsonReader.getEmail(),jsonReader.getPassword());

        new HomePage(driver)
                .logout();
        assertTrue(isLoginLinkDisplayed());

    }

    @AfterMethod
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
