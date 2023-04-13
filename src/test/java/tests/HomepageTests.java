package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;

@Epic("My Epic")
@Feature("My Feature")
public class HomepageTests {
    String firstName = "TestFN";
    String lastName = "TestLN";
    String email;
    String password = "12345678";
    WebDriver driver;
    Date date;
    @BeforeClass
    public void setup()
    {
        date = new Date();
        email = "test" + date.getTime() + "@test.com";
        System.out.println(email);
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://www.google.com/");
        BrowserActions.maximizeWindow(driver);

    }
    @Test
    public void Registration() {

        String title = BrowserActions.getPageTitle(driver);
        assertEquals(title,"Google");

    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
