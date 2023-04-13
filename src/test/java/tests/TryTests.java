package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.Logger;

import java.time.Duration;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("My Epic")
@Feature("My Feature")
public class TryTests {


    WebDriver driver;
    @BeforeClass
    @Parameters({"browser"})
    public void setup(@Optional("chrome") String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else{
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        BrowserActions.navigateToUrl(driver,"https://www.google.com/");
    }
    @Test(description = "Test Google Search")
    public void tri() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String title = BrowserActions.getPageTitle(driver);
        assertEquals(title,"Googlee");

    }
    @AfterClass
    @Description("closing browser...")

    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
