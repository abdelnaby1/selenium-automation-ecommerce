package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CartPage {
    private WebDriver driver;
    private ElementActions eleActions;
    private By productName = By.cssSelector(".product-name");
    private By qtyInput = By.className("qty-input");
    private By updateCartBtn = By.cssSelector("button#updatecart");
    private By removeProductBtn = By.cssSelector(".remove-btn");
    private By noDataText = By.cssSelector(".no-data");

    private By termsAndConditionsBtn = By.id("termsofservice");
    private By checkoutBtn = By.id("checkout");
    public CartPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }

    public String getProductName() {
        return eleActions.getText(productName);
    }

    public CartPage updateQty(String qtr) {
        eleActions
                .type(qtyInput,qtr,true)
                .click(updateCartBtn);
        return  this;
    }

    public String getQtyInput() {
        return eleActions.getAttributeValue(qtyInput,"value");
    }

    public CartPage removeprodcut() {
        eleActions.click(removeProductBtn);
        return this;
    }
    public String getNoDataText() {
        return eleActions.getText(noDataText);
    }
    public CheckoutPage checkout(){
        eleActions.click(termsAndConditionsBtn)
                .click(checkoutBtn);
        return new CheckoutPage(driver);
    }
}
