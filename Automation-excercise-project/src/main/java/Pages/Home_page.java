package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import utils.my_framework;


public class Home_page {


    private my_framework framework;
    private WebDriver driver;

    //Locators For HomePage Header Buttons
    private final By headerButtonsSection=By.cssSelector("ul[class='nav navbar-nav']");
    private final By signupLoginButtonLocator = By.cssSelector("[href='/login']");
    private final By productsButtonLocator = By.cssSelector("[href='/products']");
    private final By cartButtonHeaderLocator = By.cssSelector("a[href='/view_cart']>i");
    private final By testCasesButtonLocator = By.cssSelector("a[href='/test_cases']>i");
    private final By ApiTestingButtonLocator = By.cssSelector("a[href='/api_list'] >i");
    private final By contactUsButtonLocator = By.cssSelector("[class='fa fa-envelope']");
    private final By logoutUserButtonLocator =By.cssSelector("a[href='/logout']");

    //verify text is visible
    private final By loggedAsUsernameTextLocator =By.cssSelector("ul[class='nav navbar-nav'] li:nth-of-type(10)");

    //Delete Account Locators
    private final By deleteAccountLocator = By.cssSelector("a[href='/delete_account']");
    private final By deletedAccountTextLocators = By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p:nth-of-type(1)");
    private final By continueButtonLocatorONDeletePage = By.cssSelector("a[data-qa='continue-button']");

    //subscription section home page Locators
    private final By subscriptionTextHomePageLocator =By.cssSelector("div[class='single-widget'] h2");
    private final By subscriptionEmailHomePageLocator=By.cssSelector("input[type='email']");
    private final By subscriptionArrowButtonHomePageLocator=By.cssSelector("i[class='fa fa-arrow-circle-o-right']");
    private final By subscriptionSectionLocator=By.cssSelector("#footer div[class='single-widget'] p");

    //recommended items section Locators
    private final By recommendedItemsSectionLocator=By.cssSelector("div[class='recommended_items']");
    private final By recommendedItemsTextLocator =By.cssSelector("div[class='recommended_items'] h2[class='title text-center']");
    private final By firstRecommendedProductAddToCartButtonLocator =By.cssSelector("div[class='recommended_items'] a[data-product-id='1']");
    private final By secondRecommendedProductAddToCartButtonLocator=By.cssSelector("div[class='recommended_items'] a[data-product-id='4']");

    //scroll up arrow locator
    private final By scrollUpArrowLocator = By.cssSelector("a[id='scrollUp']");
    private final By headerTextOnHomepageLocator = By.cssSelector("div[class='col-sm-6'] h2");

    //category locators
    private final By categoryProductsSectionLocator=By.cssSelector("div[class='panel-group category-products']");
    private final By womenCategoryButtonLocator=By.cssSelector("h4[class='panel-title'] a[href='#Women']");
    private final By womenCategoryButtonDressLocator=By.cssSelector("div[id='Women'] a[href='/category_products/1']");
    private final By categoryProductsTextLocator=By.cssSelector("h2[class='title text-center']");
    private final By manCategoryButtonLocator=By.cssSelector("div[id='accordian'] a[href='#Men']");
    private final By manCategoryButtonJeansLocator=By.cssSelector("div[id='Men'] a[href='/category_products/6'] ");



    //constructor for Home page
    public Home_page(WebDriver driver) {
        this.driver = driver;
        framework = new my_framework(driver);
    }

    // <<<< Methods of "homepage" >>>>

    //verify the headers link text can be clicked successfully
    public void clickOnLoginHeaderButton() {
        framework.click(signupLoginButtonLocator);
    }

    public void clickOnSignupHeaderButton() {
        framework.click(signupLoginButtonLocator);
    }

    public void clickOnCartHeaderButton() {
        framework.click(cartButtonHeaderLocator);
    }

    public void clickOnProductsHeaderButton() {
        framework.click(productsButtonLocator);
    }

    public void clickOnTestCasesHeaderButton() {
        framework.click(testCasesButtonLocator);
    }

    public void clickOnApiTestingHeaderButton() {
        framework.click(ApiTestingButtonLocator);
    }

    public void clickContactUsHeaderButton() {
        framework.click(contactUsButtonLocator);
    }

    public String getSuccessMessage(){
        return Browser.getText(loggedAsUsernameTextLocator);
    }

    public void clickOnDeleteAccountButton() {
        framework.click(deleteAccountLocator);
    }

    public void clickLogoutUser() {
        framework.click(logoutUserButtonLocator);
    }

    //method for verify account deleted
    public String accountDeletedTextIsVisible() {
        framework.scrollToElement(deletedAccountTextLocators);
        return Browser.getText(deletedAccountTextLocators);
    }

    //method for verify subscription text is visible
    public String verifySubscriptionText() {
        framework.scrollToElement(subscriptionSectionLocator);
        return Browser.getText(subscriptionTextHomePageLocator);
    }

    public void enterSubscriptionEmail(String email){
        framework.scrollToElement(subscriptionSectionLocator);
        framework.sendKeys(subscriptionEmailHomePageLocator,email);
    }

    public void clickSubscriptionArrowButton(){
        framework.scrollToElement(subscriptionSectionLocator);
        framework.click(subscriptionArrowButtonHomePageLocator);
    }

    public boolean verifyRecommendedItemsText(){
        framework.scrollToElement(recommendedItemsSectionLocator);
       return framework.displayElement(recommendedItemsTextLocator);
    }

    public void clickAddToCartButtonFromRecommendedItems(){
        framework.scrollToElement(recommendedItemsSectionLocator);
        try {
            if (framework.displayElement(firstRecommendedProductAddToCartButtonLocator)) {
                framework.click(firstRecommendedProductAddToCartButtonLocator);
            }
        } catch (Exception e) {
            System.out.println("add to cart button for first recommended product items not visible ");
        }
        try {
            if (framework.displayElement(secondRecommendedProductAddToCartButtonLocator)) {
                framework.click(secondRecommendedProductAddToCartButtonLocator);
            }
        } catch (Exception e) {
            System.out.println("add to cart button second recommended product items not visible ");
        }
    }

    public void clickOnScrollUpArrow(){
        framework.scrollToElement(subscriptionSectionLocator);
        framework.click(scrollUpArrowLocator);
    }

    public String verifyHeaderTextOnHomepageIsVisible() {
        framework.scrollToElement(headerButtonsSection);
        return Browser.getText(headerTextOnHomepageLocator);
    }

    public boolean verifyCategoriesVisibleOnLeftSide(){
        framework.scrollToElement(categoryProductsSectionLocator);
        return framework.displayElement(categoryProductsSectionLocator);
    }

    public void clickOnWomenButtonCategory(){
        framework.scrollToElement(categoryProductsSectionLocator);
        framework.click(womenCategoryButtonLocator);
        framework.click(womenCategoryButtonDressLocator);
    }

    public String verifyCategoryProductsText(){
        framework.scrollToElement(categoryProductsTextLocator);
        return Browser.getText(categoryProductsTextLocator);
    }

    public void clickOnManButtonCategory(){
        framework.scrollToElement(categoryProductsSectionLocator);
        framework.click(manCategoryButtonLocator);
        framework.click(manCategoryButtonJeansLocator);
    }

}