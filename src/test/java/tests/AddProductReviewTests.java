package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.util.Date;

import static org.testng.Assert.assertEquals;

public class AddProductReviewTests {
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
    public void testAddProductReview(){
       String message =
               new SearchPage(driver)
               .searchByKeyword("laptop")
               .openProductReviewPage()
               .rate("Bad product","this is un profissional product","Bad")
               .getMessage();
       assertEquals(message, "Product review is successfully added.");
    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
