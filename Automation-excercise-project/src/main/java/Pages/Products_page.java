package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import utils.my_framework;

import java.util.ArrayList;
import java.util.List;

public class Products_page {

    private WebDriver driver;
    private my_framework framework;

    // <<<<< Locators of Products_page  >>>>>

    //first product locators
    private final By productListLocator=By.cssSelector("h2[class='title text-center']");
    private final By firstProductsDetailsLocator =By.cssSelector("div[class='product-information']");
    private final By viewFirstProductLocator=By.cssSelector("a[href='/product_details/1']");
    private final By nameOfFirstProductLocator=By.cssSelector("div[class='product-information'] h2");
    private final By categoryOfFirstProductLocator=By.cssSelector("div[class='product-information'] p:nth-of-type(1)");
    private final By priceOfFirstProductLocator=By.xpath("//span[text()='Rs. 500']");
    private final By availabilityOfFirstProductLocator=By.cssSelector("div[class='product-information'] p:nth-of-type(2)");
    private final By conditionOfFirstProductLocator=By.cssSelector("div[class='product-information'] p:nth-of-type(3)");
    private final By brandOfFirstProductLocator=By.cssSelector("div[class='product-information'] p:nth-of-type(4)");
    private final By firstProductQuantityLocator =By.cssSelector("input[name='quantity']");
    private final By addToCartButtonLocator=By.cssSelector("button[class='btn btn-default cart']");

    //search product locators
    private final By searchProductFieldLocator =By.cssSelector("input#search_product");
    private final By searchProductButtonLocator=By.cssSelector("i[class='fa fa-search']");
    private final By searchedProductTextLocator=By.cssSelector("h2[class='title text-center']");
    private final By listOfCategory=By.cssSelector("div#accordian");
    private final By sareeTextLocator=By.cssSelector("div[class='productinfo text-center'] p");

    //add product to cart locators
    private final By addFirstProductToCartLocator=By.cssSelector("div[class='product-image-wrapper']  a[data-product-id='1']");
    private final By addToCartFirstProductButtonLocator =By.cssSelector("button[class='btn btn-success close-modal btn-block']");
    private final By addSecondProductToCartLocator =By.cssSelector("div[class='product-image-wrapper']  a[data-product-id='2']");
    private final By viewSecondProductLocator=By.cssSelector("a[href='/product_details/2']");
    private final By viewCartLocator=By.cssSelector("p[class='text-center'] a[href='/view_cart']");

    //review product locators
    private final By reviewProductSectionLocator =By.cssSelector("div[class='category-tab shop-details-tab']");
    private final By writeReviewProductTextLocator=By.cssSelector("a[href='#reviews']");
    private final By reviewNameFieldLocator =By.cssSelector("input[placeholder='Your Name']");
    private final By reviewEmailAddressFieldLocator =By.cssSelector("input[placeholder='Email Address']");
    private final By writeReviewFieldLocator =By.cssSelector("textarea[placeholder='Add Review Here!']");
    private final By reviewSubmitButtonLocator =By.cssSelector("button[id='button-review']");
    private final By reviewSuccessMessageLocator =By.cssSelector("div[class='alert-success alert'] span ");

    //brand section locators
    private final By brandSectionLocator =By.cssSelector("div[class='brands-name']");
    private final By madameBrandButtonLocator =By.cssSelector("div[class='brands-name'] li:nth-of-type(3)");
    private final By kidsBrandButtonLocator =By.cssSelector("div[class='brands-name'] li:nth-of-type(7)");
    private final By brandItemsLocator=By.cssSelector("div[class='features_items']");
    private final By viewProductButtonSareeLocator =By.cssSelector("a[href='/product_details/41']");

    //add searched products to cart
    private final By addFirstProductFromSearchedLocator=By.cssSelector("div[class='product-image-wrapper']  a[data-product-id='39']");
    private final By addSecondProductFromSearchedLocator=By.cssSelector("div[class='product-image-wrapper']  a[data-product-id='40']");
    private final By addThirdProductFromSearchedLocator=By.cssSelector("div[class='product-image-wrapper']  a[data-product-id='41']");
    private final By continueShoppingLocator=By.cssSelector("button[data-dismiss='modal']");


    //constructor for Products_page
    public Products_page(WebDriver driver){
        this.driver=driver;
        framework=new my_framework(driver);
    }

    // <<<<<< Methods Of Products >>>>>>>

    // <<<<< Verify All Products and product detail page method >>>>>
    public String verifyProductListPage(){
        framework.scrollToElement(productListLocator);
        return Browser.getText(productListLocator);
    }

    public void clickOnViewFirstProduct(){
        framework.scrollToElement(viewFirstProductLocator);
        framework.click(viewFirstProductLocator);
    }

    //get all the details of first product that not display
    public List<String> verifyFirstProductDetails(){

        List<String> notDisplayed = new ArrayList<>();

        framework.scrollToElement(firstProductsDetailsLocator);

        framework.checkAndAddIfNotDisplayed(nameOfFirstProductLocator,
                "name of first product is not displayed", notDisplayed);

        framework.checkAndAddIfNotDisplayed(categoryOfFirstProductLocator,
                "category of first product is not displayed",notDisplayed);

       framework.checkAndAddIfNotDisplayed(priceOfFirstProductLocator,
               "price of first product is not displayed",notDisplayed);

        framework.checkAndAddIfNotDisplayed(availabilityOfFirstProductLocator,
                "availability of first product is not displayed",notDisplayed);

        framework.checkAndAddIfNotDisplayed(conditionOfFirstProductLocator,
                "condition of first product is not displayed",notDisplayed);

        framework.checkAndAddIfNotDisplayed(brandOfFirstProductLocator,
                "brand of first product is not displayed",notDisplayed);

        return notDisplayed;
    }

    public void enterFirstProductQuantity(String quantity){
        framework.clear(firstProductQuantityLocator);
        framework.sendKeys(firstProductQuantityLocator,quantity);
    }

    public void clickOnAddToCartButton(){
        framework.scrollToElement(addToCartButtonLocator);
        framework.click(addToCartButtonLocator);
    }

    // <<<<< search for a product methods >>>>>
    public void enterProductNameOnSearchField(String text){
        framework.scrollToElement(viewFirstProductLocator);
        framework.sendKeys(searchProductFieldLocator,text);
    }

    public void clickOnSearchProductButton(){
        framework.scrollToElement(viewFirstProductLocator);
        framework.click(searchProductButtonLocator);
    }

    public String verifySearchedProductsText(){
        framework.scrollToElement(listOfCategory);
        return Browser.getText(searchedProductTextLocator);
    }

    // <<<<< Verify All Products and product detail page method >>>>>
    public void addFirstProductOnCart(){
        framework.scrollToElement(viewFirstProductLocator);
        framework.click(addFirstProductToCartLocator);
    }

    public void clickOnContinueShoppingButton(){
        framework.click(addToCartFirstProductButtonLocator);
    }

    public void addSecondProductOnCart(){
        framework.scrollToElement(viewSecondProductLocator);
        framework.click(addSecondProductToCartLocator);
    }

    public void clickOnViewCartButton(){
        framework.click(viewCartLocator);
    }

    public String verifyWriteReviewProductText(){
        framework.scrollToElement(reviewProductSectionLocator);
        return Browser.getText(writeReviewProductTextLocator);
    }

    public void fillOutReviewFieldsSection(String name,String email,String review){
        framework.scrollToElement(reviewProductSectionLocator);
        framework.sendKeys(reviewNameFieldLocator,name);
        framework.sendKeys(reviewEmailAddressFieldLocator,email);
        framework.sendKeys(writeReviewFieldLocator,review);
    }

    public void clickSubmitReviewButton(){
        framework.scrollToElement(reviewProductSectionLocator);
        framework.click(reviewSubmitButtonLocator);
    }

    public String verifyReviewSuccessMessage(){
        framework.scrollToElement(reviewProductSectionLocator);
        return Browser.getText(reviewSuccessMessageLocator);
    }

    public boolean verifyBrandIsVisibleOnLeftSide(){
        framework.scrollToElement(brandSectionLocator);
        return framework.displayElement(brandSectionLocator);
    }

    public void clickOnMadameBrandButton(){
        framework.scrollToElement(brandSectionLocator);
        framework.click(madameBrandButtonLocator);
    }

    public void clickOnKookieKidsBrandButton(){
        framework.scrollToElement(brandSectionLocator);
        framework.click(kidsBrandButtonLocator);
    }

    public boolean verifyBrandItemsIsVisible(){
        framework.scrollToElement(brandItemsLocator);
        return framework.displayElement(brandItemsLocator);
    }

    public String verifyNameOfSameProducts(){
        framework.scrollToElement(viewProductButtonSareeLocator);
        return Browser.getText(sareeTextLocator);
    }

    public void clickOnAddToCartFromSearchedProducts(){
        framework.scrollToElement(viewProductButtonSareeLocator);
        framework.click(addFirstProductFromSearchedLocator);
        framework.click(continueShoppingLocator);
        framework.click(addSecondProductFromSearchedLocator);
        framework.click(continueShoppingLocator);
        framework.click(addThirdProductFromSearchedLocator);
        framework.click(continueShoppingLocator);
    }

}
