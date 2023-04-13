package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.fail;

public class BrowserActions {
    static WebDriver driver;

    public enum ConfirmAlertType {
        ACCEPT, DISMISS;
    }

    public enum CookieBuilderType {
        ADD, DELETE;
    }

//    @Step("Navigating to URL: [{url}]")
    public static void navigateToUrl(WebDriver driver, String url) {
        try {
            Logger.logStep("[Browser Action] Navigate to URL [" + url + "]");
            driver.get(url);
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
    }

//    @Step("Closing All Opened Browser Windows.....")
    public static void closeAllOpenedBrowserWindows(WebDriver driver) {
        Logger.logStep("[Browser Action] Close all Opened Browser Windows");
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException rootCauseException) {
                Logger.logStep(rootCauseException.getMessage());
            } finally {
                driver = null;
            }
        } else {
            Logger.logStep("Windows are already closed and the driver object is null.");
        }
    }

//    @Step("Maximize the Browser Window")
    public static void maximizeWindow(WebDriver driver) {
        try {
            Logger.logStep("[Browser Action] Maximize the Browser Window");
            driver.manage().window().maximize();
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
        }
    }

//    @Step("Set the Browser Window Resolution")
    public static void setWindowResolution(WebDriver driver) {
        String width = System.getProperty("width");
        String height = System.getProperty("height");
        try {
            Logger.logStep(
                    "[Browser Action] Set Window Resolution as Width [" + width + "] and Height [" + height + "]");
            Dimension dimension = new Dimension(Integer.parseInt(width), Integer.parseInt(height));
            driver.manage().window().setSize(dimension);
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
        }
    }

    public static void confirmAlert(WebDriver driver, ConfirmAlertType confirmAlerType) {
        Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        switch (confirmAlerType) {
            case ACCEPT:
                alert.accept();
                break;
            case DISMISS:
                Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
                alert.dismiss();
                break;
        }
    }

    public static void cookieBuilder(WebDriver driver, CookieBuilderType cookieBuilderType, String name, String value,
                                     String domain) {
        Cookie cookie = new Cookie.Builder(name, value).domain(domain).build();

        switch (cookieBuilderType) {
            case ADD:
                driver.manage().addCookie(cookie);
                break;
            case DELETE:
                driver.manage().deleteCookie(cookie);
                break;
        }
    }

    //////////////// Getters ////////////////

    public static String getPageTitle(WebDriver driver) {
        String title = "";
        try {
            title = driver.getTitle();
            Logger.logStep("[Browser Action] Get page Title [" + title + "]");
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        return title;
    }
}
