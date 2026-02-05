package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import utils.my_framework;

public class Cart_page {

    private WebDriver driver;
    private my_framework framework;

    //subscription section cart page locators
    private final By subscriptionTextCartPageLocator=By.cssSelector("#footer div[class='single-widget'] h2");
    private final By subscriptionEmailCartPageLocator =By.cssSelector("input[type='email']");
    private final By subscriptionArrowButtonCartPageLocator =By.cssSelector("button[type='submit']");
    private final By subscriptionSectionLocator=By.cssSelector("#footer form[class='searchform'] p");

    //verify products and their information locators
    private final By firstProductInCartLocator =By.cssSelector("tr[id='product-1']");
    private final By secondProductInCartLocator =By.cssSelector("tr[id='product-2']");
    private final By firstProductPriceLocator=By.cssSelector("tr[id='product-1'] td:nth-of-type(3) p");
    private final By firstProductQuantityLocator=By.cssSelector("tr[id='product-1'] td:nth-of-type(4) button");
    private final By firstProductTotalPriceLocator=By.cssSelector("tr[id='product-1'] td:nth-of-type(5) p");
    private final By secondProductPriceLocator=By.cssSelector("tr[id='product-2'] td:nth-of-type(3) p");
    private final By secondProductQuantityLocator=By.cssSelector("tr[id='product-2'] td:nth-of-type(4) button");
    private final By secondProductTotalPriceLocator=By.cssSelector("tr[id='product-2'] td:nth-of-type(5) p");

    //check quantity of product locators
    private final By quantityOfFirstProductLocator =By.cssSelector("button[class='disabled']");

    //recommended products locators
    private final By productsTableInfoInCart =By.cssSelector("div[class='table-responsive cart_info']");
    private final By productFromRecommendedItemsInCartLocator =By.cssSelector("tr[id='product-4']");
    private final By secondProductFromRecommendedItemsInCartLocator =By.cssSelector("tr[id='product-1']");

    //remove products from cart locators
    private final By deleteProductFromCartLocator=By.cssSelector("a[class='cart_quantity_delete']");

    //proceed to checkout products from cart locators
    private final By checkoutLocator =By.cssSelector("div[class='modal-content']");
    private final By checkoutButtonLocator=By.cssSelector("a[class='btn btn-default check_out']");
    private final By register_LoginButtonLocator=By.cssSelector("div[class='modal-content'] a[href='/login']");

    //add searched products to cart locators
    private final By firstProductsFromSearchedLocator=By.cssSelector("div[id='cart_info'] img[src='get_product_picture/39']");
    private final By secondProductsFromSearchedLocator=By.cssSelector("div[id='cart_info'] img[src='get_product_picture/40']");
    private final By thirdProductsFromSearchedLocator=By.cssSelector("div[id='cart_info'] img[src='get_product_picture/41']");


    //constructor for cart page
    public Cart_page(WebDriver driver){
        this.driver=driver;
        framework=new my_framework(driver);
    }

    //<<<< Methods of "Verify Subscription in Cart page" >>>>
    public String verifySubscriptionText() {
        framework.scrollToElement(subscriptionSectionLocator);
        return Browser.getText(subscriptionTextCartPageLocator);
    }

    public void enterSubscriptionEmail(String email){
        framework.scrollToElement(subscriptionSectionLocator);
        framework.sendKeys(subscriptionEmailCartPageLocator,email);
    }

    public void clickSubscriptionArrowButton(){
        framework.scrollToElement(subscriptionSectionLocator);
        framework.click(subscriptionArrowButtonCartPageLocator);
    }

    //<<<< Methods of "Verify Products in Cart page" >>>>
    public boolean verifyFirstProductDisplayInCart() {
        framework.scrollToElement(firstProductInCartLocator);
        return framework.displayElement(firstProductInCartLocator);
    }

    public boolean verifySecondProductDisplayInCart(){
        framework.scrollToElement(secondProductInCartLocator);
        return framework.displayElement(secondProductInCartLocator);
    }

    public void verifyFirstProductInfoInCart() {
        if (framework.displayElement(secondProductInCartLocator)) {
            framework.scrollToElement(secondProductInCartLocator);
        }else {
            framework.scrollToElement(firstProductInCartLocator);
        }
        if (firstProductTotalPriceLocator != null) {
            boolean firstProductPriceDisplayed = framework.displayElement(firstProductPriceLocator);
            System.out.println("first product price displayed: " + firstProductPriceDisplayed);
        } else {
            System.out.println("first product price in the shopping cart are not visible");
        }
        if (firstProductQuantityLocator != null) {
            boolean firstProductQuantityDisplayed = framework.displayElement(firstProductQuantityLocator);
            System.out.println("first product quantity displayed: " + firstProductQuantityDisplayed);
        } else {
            System.out.println("first product quantity in the shopping cart are not visible");
        }
        if (firstProductTotalPriceLocator != null) {
            boolean firstProductTotalPriceDisplayed = framework.displayElement(firstProductTotalPriceLocator);
            System.out.println("first product total price displayed: " + firstProductTotalPriceDisplayed);
        } else {
            System.out.println("first product total price in the shopping cart are not visible");
        }
    }

    public void verifySecondProductInfoInCart(){
        framework.scrollToElement(secondProductInCartLocator);

        if (secondProductPriceLocator != null) {
            framework.scrollToElement(secondProductInCartLocator);
            boolean secondProductPriceDisplayed = framework.displayElement(secondProductPriceLocator);
            System.out.println("second product price displayed: " + secondProductPriceDisplayed);
        } else {
            System.out.println("second product price in the shopping cart are not visible");
        }
        if (secondProductQuantityLocator != null) {
            boolean secondProductQuantityDisplayed = framework.displayElement(secondProductQuantityLocator);
            System.out.println("second product quantity displayed: " + secondProductQuantityDisplayed);
        } else {
            System.out.println("second product quantity in the shopping cart are not visible");
        }
        if (secondProductTotalPriceLocator != null) {
            boolean secondProductTotalPriceDisplayed = framework.displayElement(secondProductTotalPriceLocator);
            System.out.println("second product total price displayed: " + secondProductTotalPriceDisplayed);
        } else {
            System.out.println("second product total price in the shopping cart are not visible");
        }
    }

    public String verifyQuantityOfFirstProduct(){
        framework.scrollToElement(quantityOfFirstProductLocator);
        return Browser.getText(quantityOfFirstProductLocator);
    }

    //<<<< Method of "Verify recommended product in Cart page" >>>>

    public boolean verifyRecommendedProductsAddedToCart() {

        boolean productIsVisibleInCart = false;
        boolean otherProductIfVisibleInCart = false;

        framework.scrollToElement(productsTableInfoInCart);
        try {
            productIsVisibleInCart = framework.displayElement(productFromRecommendedItemsInCartLocator);
        } catch (Exception e) {
        }
        try {
            otherProductIfVisibleInCart = framework.displayElement(secondProductFromRecommendedItemsInCartLocator);
        } catch (Exception e) {
        }
        return productIsVisibleInCart || otherProductIfVisibleInCart;
    }

    //<<<< Method of "removed product from Cart page" >>>>

    public void clickOnXButton(){
        framework.scrollToElement(productsTableInfoInCart);
        framework.click(deleteProductFromCartLocator);

    }

    //<<<< Method of "Verify product is removed from Cart page" >>>>

    public boolean verifyDeletionOfProductFromCart() {
        try {
            return !framework.displayElement(firstProductInCartLocator);
        } catch (Exception e) {
            return true;
        }
    }

    public void clickOnProceedToCheckoutButton(){
        framework.scrollToElement(productsTableInfoInCart);
        framework.click(checkoutButtonLocator);
    }

    public void clickOnRegisterFromCheckoutButton(){
        framework.scrollToElement(checkoutLocator);
        framework.click(register_LoginButtonLocator);
    }

    public boolean verifyFirstSearchedProductsVisibleInCart(){
        framework.scrollToElement(thirdProductsFromSearchedLocator);
        try {
            return framework.displayElement(firstProductsFromSearchedLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifySecondSearchedProductsVisibleInCart(){
        framework.scrollToElement(thirdProductsFromSearchedLocator);
        try {
            return framework.displayElement(secondProductsFromSearchedLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyThirdSearchedProductsVisibleInCart(){
        framework.scrollToElement(thirdProductsFromSearchedLocator);
        try {
            return framework.displayElement(thirdProductsFromSearchedLocator);
        } catch (Exception e) {
            return false;
        }
    }

}



