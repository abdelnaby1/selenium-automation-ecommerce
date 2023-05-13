package tests;

import data.ExcelReader;
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

import java.io.IOException;
import java.util.Date;

import static org.testng.Assert.assertTrue;
import static pages.HomePage.isLoginLinkDisplayed;

public class RegistrationWithDDTExclTests {
    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }
    @DataProvider(name = "userData")
    public static Object[][] userData() throws IOException {
        String filepath = System.getProperty("user.dir")+"/src/test/resources/testData/userData.xlsx";
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getExcelData(filepath);
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

    @AfterMethod
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
