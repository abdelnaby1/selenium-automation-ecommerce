package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Helper {
    private static int getTimeout() {
        return Integer.parseInt(System.getProperty("webdriver.wait"));
    }

    public static WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(getTimeout()));
    }

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getTimeout()));
    }

    public static String getCurrentTime(String dateFormat) {
        String currentTime = "";
        try {
            currentTime = new SimpleDateFormat(dateFormat).format(new Date());
        } catch (IllegalArgumentException e) {
            Logger.logStep(e.getMessage());
            fail(e.getMessage());
        }
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("ddMMyyyyHHmmssSSS");
    }

    public static int getRandomNumberBetweenTwoValues(int lowValue, int highValue) {
        return new Random().nextInt(highValue - lowValue) + lowValue;
    }

    public static String getRandomNumberBetweenTwoValuesAsString(int lowValue, int highValue) {
        return Integer.toString(getRandomNumberBetweenTwoValues(lowValue, highValue));
    }
    //ORDER NUMBER: 1031
    public static String extractOrderNumber(String text) {
        String[] arrOfStr = text.split(" ", 3);
        String number = "";
        try{
            number =arrOfStr[2];

        } catch(NumberFormatException ex){ // handle your exception
        }
        return number;
    }
}
