package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import data.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterResultPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.testng.Assert.assertTrue;
import static pages.HomePage.isLoginLinkDisplayed;

public class RegistrationWithDDTCSVTests {
    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);

    }

    @Test
    public void testValidRegister() throws IOException, CsvValidationException {
        String filepath = System.getProperty("user.dir") + "/src/test/resources/testData/userData.csv";
        CSVReader csvReader = new CSVReader(new FileReader(filepath));
        String[] csvCell;
        while ((csvCell = csvReader.readNext()) != null){
            String fName = csvCell[0];
            String lName = csvCell[1];
            String email = csvCell[2];
            String password = csvCell[3];

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


    }

    @AfterMethod
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
