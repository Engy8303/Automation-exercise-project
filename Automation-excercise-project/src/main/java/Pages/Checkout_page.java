package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import utils.my_framework;

import java.util.ArrayList;
import java.util.List;

public class Checkout_page {

    private WebDriver driver;
    private my_framework framework;

    //<<<<<<<< Locators of checkout >>>>>>>>>

    //checkout locators
    private final By addressDetailsLocator=By.cssSelector("div[class='checkout-information']");
    private final By reviewOrderLocator=By.cssSelector("div[class='table-responsive cart_info']");
    private final By commentOnOrderFieldLocator=By.cssSelector("textarea[class='form-control']");
    private final By placeOrderButtonLocator=By.cssSelector("a[class='btn btn-default check_out']");

    //payment locators
    private final By paymentSectionLocator=By.cssSelector("div[class='container'] form[action='/payment']");
    private final By cardNameLocator =By.cssSelector("input[name='name_on_card']");
    private final By cardNumberLocator=By.cssSelector("input[name='card_number']");
    private final By cvcLocator=By.cssSelector("input[data-qa='cvc']");
    private final By expirationMonthLocator=By.cssSelector("input[class='form-control card-expiry-month']");
    private final By expirationYearLocator=By.cssSelector("input[data-qa='expiry-year']");
    private final By payAndConfirmOrderLocator=By.cssSelector("button[class='form-control btn btn-primary submit-button']");
    private final By orderSuccessMsgLocator=By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p");
    private final By downloadInvoiceButtonLocator=By.cssSelector("a[href='/download_invoice/500']");
    private final By continueButtonLocator=By.cssSelector("a[data-qa='continue-button']");

    //delivery address locators
    private final By deliveryAddressNameLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(2)");
    private final By deliveryAddressCompanyLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(3)");
    private final By deliveryAddressFirstAddressLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(4)");
    private final By deliveryAddressSecondAddressLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(5)");
    private final By deliveryAddressCityLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(6)");
    private final By deliveryAddressCountryLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(7)");
    private final By deliveryAddressPhoneNumberLocator=By.cssSelector("ul[class='address item box'] li:nth-of-type(8)");

    //billing address locators
    private final By billingAddressNameLocator =By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(2)");
    private final By billingAddressCompanyLocator=By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(3)");
    private final By billingAddressFirstAddressLocator=By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(4)");
    private final By billingAddressSecondAddressLocator=By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(5)");
    private final By billingAddressCityLocator=By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(6)");
    private final By billingAddressCountryLocator=By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(7)");
    private final By billingAddressPhoneNumberLocator=By.cssSelector("ul[class='address alternate_item box'] li:nth-of-type(8)");


    //constructor for checkout page
    public Checkout_page(WebDriver driver){
        this.driver=driver;
        framework=new my_framework(driver);
    }


    //<<<< Methods of "checkout and payment page" >>>>

    public boolean verifyAddressDetails_reviewOrderIsVisible(){
        framework.scrollToElement(addressDetailsLocator);
        framework.displayElement(addressDetailsLocator);
        framework.scrollToElement(reviewOrderLocator);
        return framework.displayElement(reviewOrderLocator);
    }

    public void enterCommentOnOrder(String comment){
        framework.scrollToElement(commentOnOrderFieldLocator);
        framework.sendKeys(commentOnOrderFieldLocator,comment);
    }

    public void clickOnPlaceOrderButton(){
        framework.scrollToElement(placeOrderButtonLocator);
        framework.click(placeOrderButtonLocator);
    }

    public void enterPaymentFields(String cardName,String cardNumber,String cvc,String expirationMonth,String expirationYear){
        framework.scrollToElement(paymentSectionLocator);
        framework.sendKeys(cardNameLocator,cardName);
        framework.sendKeys(cardNumberLocator,cardNumber);
        framework.sendKeys(cvcLocator,cvc);
        framework.sendKeys(expirationMonthLocator,expirationMonth);
        framework.sendKeys(expirationYearLocator,expirationYear);
    }

    public void clickOnPayAndConfirmButton(){
        framework.scrollToElement(payAndConfirmOrderLocator);
        framework.click(payAndConfirmOrderLocator);
    }

    public String verifyOrderSuccessMessageText(){
        framework.scrollToElement(orderSuccessMsgLocator);
        return Browser.getText(orderSuccessMsgLocator);
    }

    public void clickOnDownloadInvoiceButton(){
        framework.scrollToElement(downloadInvoiceButtonLocator);
        framework.click(downloadInvoiceButtonLocator);
    }

    public void clickOnContinueButton(){
        framework.scrollToElement(continueButtonLocator);
        framework.click(continueButtonLocator);
    }

    //<<<< Methods for "verifying the delivery address" >>>>

    public String verifyDeliveryName(){
        framework.scrollToElement(deliveryAddressNameLocator);
        return Browser.getText(deliveryAddressNameLocator);
    }

    public String verifyDeliveryCompany(){
        framework.scrollToElement(deliveryAddressCompanyLocator);
        return Browser.getText(deliveryAddressCompanyLocator);
    }

    public String verifyDeliveryFirstAddress(){
        framework.scrollToElement(deliveryAddressFirstAddressLocator);
        return Browser.getText(deliveryAddressFirstAddressLocator);
    }

    public String verifyDeliverySecondAddress(){
        framework.scrollToElement(deliveryAddressSecondAddressLocator);
        return Browser.getText(deliveryAddressSecondAddressLocator);
    }

    public String verifyDeliveryCity(){
        framework.scrollToElement(deliveryAddressCityLocator);
        return Browser.getText(deliveryAddressCityLocator);
    }

    public String verifyDeliveryCountry(){
        framework.scrollToElement(deliveryAddressCountryLocator);
        return Browser.getText(deliveryAddressCountryLocator);
    }

    public String verifyDeliveryPhoneNumber(){
        framework.scrollToElement(deliveryAddressPhoneNumberLocator);
        return Browser.getText(deliveryAddressPhoneNumberLocator);
    }

    public String verifyDeliveryAddressSameAsRegistration(){
        framework.scrollToElement(reviewOrderLocator);
        return  Browser.getText(deliveryAddressNameLocator)+ "\n"+
        Browser.getText(deliveryAddressCompanyLocator)+ "\n"+
        Browser.getText(deliveryAddressFirstAddressLocator)+ "\n"+
        Browser.getText(deliveryAddressSecondAddressLocator)+ "\n"+
        Browser.getText(deliveryAddressCityLocator)+ "\n"+
        Browser.getText(deliveryAddressCountryLocator)+ "\n"+
        Browser.getText(deliveryAddressPhoneNumberLocator);
    }

    //<<<< Methods for "verifying the billing address" >>>>

    public String verifyBillingName(){
        framework.scrollToElement(billingAddressNameLocator);
        return Browser.getText(billingAddressNameLocator);
    }

    public String verifyBillingCompany(){
        framework.scrollToElement(billingAddressCompanyLocator);
        return Browser.getText(billingAddressCompanyLocator);
    }

    public String verifyBillingFirstAddress(){
        framework.scrollToElement(billingAddressFirstAddressLocator);
        return Browser.getText(billingAddressFirstAddressLocator);
    }

    public String verifyBillingSecondAddress(){
        framework.scrollToElement(billingAddressSecondAddressLocator);
        return Browser.getText(billingAddressSecondAddressLocator);
    }

    public String verifyBillingCity(){
        framework.scrollToElement(billingAddressCityLocator);
        return Browser.getText(billingAddressCityLocator);
    }

    public String verifyBillingCountry(){
        framework.scrollToElement(billingAddressCountryLocator);
        return Browser.getText(billingAddressCountryLocator);
    }

    public String verifyBillingPhoneNumber(){
        framework.scrollToElement(billingAddressPhoneNumberLocator);
        return Browser.getText(billingAddressPhoneNumberLocator);
    }

    public String verifyBillingAddressSameAsRegistration() {
        framework.scrollToElement(reviewOrderLocator);
        return Browser.getText(billingAddressNameLocator) + "\n"
                + Browser.getText(billingAddressCompanyLocator) + "\n"
                + Browser.getText(billingAddressFirstAddressLocator) + "\n"
                + Browser.getText(billingAddressSecondAddressLocator) + "\n"
                + Browser.getText(billingAddressCityLocator) + "\n"
                + Browser.getText(billingAddressCountryLocator) + "\n"
                + Browser.getText(billingAddressPhoneNumberLocator);

    }
}
