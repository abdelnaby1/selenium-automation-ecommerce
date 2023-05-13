package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class CheckoutPage {

    private WebDriver driver;
    private ElementActions eleActions;
    private By countyField = By.id("BillingNewAddress_CountryId");
    private By cityField = By.id("BillingNewAddress_City");
    private By address1Field = By.id("BillingNewAddress_Address1");
    private By zipCodeField = By.id("BillingNewAddress_ZipPostalCode");
    private By phoneNumberField = By.id("BillingNewAddress_PhoneNumber");
    private By continueBtn = By.cssSelector("#billing-buttons-container .button-1.new-address-next-step-button");
    private By shippingMethodContinueBtn = By.cssSelector("#shipping-method-buttons-container .button-1.shipping-method-next-step-button");
    private By paymentContinueBtn = By.cssSelector("#payment-method-buttons-container .button-1.payment-method-next-step-button");
    private By paymentIfoContinueBtn = By.cssSelector("#payment-info-buttons-container .button-1.payment-info-next-step-button");
    private By confirmCheckoutBtn = By.cssSelector("#confirm-order-buttons-container .button-1.confirm-order-next-step-button");
    private By title = By.cssSelector(".section.order-completed .title strong");
    private By orderNumber = By.cssSelector(".order-number strong");
    private By continueBtnAfterOrderCompleted = By.id("button-1 order-completed-continue-button");
    private By orderDetailsBtn = By.cssSelector(".details-link a");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        eleActions = new ElementActions(driver);
    }

    public CheckoutPage fillTheAddressDetails(String county, String city, String address, String zipcode, String phone){
         eleActions.select(countyField, ElementActions.SelectType.TEXT,county)
                 .type(cityField,city)
                 .type(address1Field,address)
                 .type(zipCodeField,zipcode)
                 .type(phoneNumberField,phone);
         if (eleActions.isElementDisplayed(continueBtn)){
             eleActions.mouseHover(continueBtn).click(continueBtn);
         }
         return this;
    }
    public CheckoutPage chooseDefaultShippingMethod(){
        eleActions.click(shippingMethodContinueBtn);
        return this;
    }
    public CheckoutPage chooseDefaultPaymentMethod(){
        eleActions.click(paymentContinueBtn);
        return this;
    }
    public CheckoutPage continueOnPaymentInfo(){
        eleActions.click(paymentIfoContinueBtn);
        return this;
    }
    public CheckoutPage confirmCheckout(){
        eleActions.click(confirmCheckoutBtn);
        return this;
    }

    public String getTitle() {
        return eleActions.getText(title);
    }
    public String getOrderNumber() {
        return eleActions.getText(orderNumber);
    }

    public HomePage continueBtnAfterOrderCompleted() {
        eleActions.click(continueBtnAfterOrderCompleted);
        return new HomePage(driver);
    }
    public OrderDetailsPage openOrderDetails(){
        eleActions.click(orderDetailsBtn);
        return new OrderDetailsPage(driver);
    }
}
