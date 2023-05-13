package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class OrderDetailsPage {
    private WebDriver driver;
    private ElementActions eleActions;
    private By printBtn = By.cssSelector(".button-2.print-order-button");
    private By printInvoicBtn = By.cssSelector(".button-2.pdf-invoice-button");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.eleActions = new ElementActions(driver);
    }
    public OrderDetailsPage downloadPDFOrderInvoice(){
        eleActions.click(printInvoicBtn);
        return this;
    }
    public OrderDetailsPage printOrderDetails(){
        eleActions.click(printBtn);
        return this;
    }
}
