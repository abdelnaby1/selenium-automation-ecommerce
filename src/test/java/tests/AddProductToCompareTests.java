package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ComparePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;
import utils.BrowserActions;
import utils.BrowserFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class AddProductToCompareTests {
    WebDriver driver;
    List productsName = new ArrayList();

    @BeforeClass
    public void setup()
    {
        driver = BrowserFactory.getBrowser();
        BrowserActions.navigateToUrl(driver,"https://demo.nopcommerce.com/");
        BrowserActions.maximizeWindow(driver);


    }
    @DataProvider(name = "searchKeywords")
    public Object[][] getKeywords() {
        return new Object [][] {
                {"mac"},
                {"samsung"}
        };
    }
    @Test(dataProvider = "searchKeywords")
    public void testAddToCompare(String keyword) {
        String toastMsg =
                new SearchPage(driver)
                .searchByKeyword(keyword)
                        .addProductToCompare()
                        .getToastMessage();
         productsName.add(new ProductDetailsPage(driver).getProductName());
        assertTrue(toastMsg.equalsIgnoreCase("The product has been added to your product comparison"));

    }
    @Test(dependsOnMethods = "testAddToCompare")
    public void testComparePage() {
        driver.get("https://demo.nopcommerce.com/compareproducts");
        var productsCompareName = new ComparePage(driver).getProductsName();
        assertEquals(productsCompareName.get(1),productsName.get(0));
        assertEquals(productsCompareName.get(0),productsName.get(1));
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/screenshots/compare.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test(dependsOnMethods = "testComparePage")
    public void testClearList() {
       String noDataText =
               new ComparePage(driver)
               .clearList()
               .getNoDataText();
       assertEquals(noDataText,"You have no items to compare.");

    }
    @AfterClass
    public void closeBrowser() {
        BrowserActions.closeAllOpenedBrowserWindows(driver);

    }
}
