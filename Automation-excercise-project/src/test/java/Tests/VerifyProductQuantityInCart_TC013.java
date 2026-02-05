package Tests;

import Browser.driverFactory;
import Pages.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.Products;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;
import java.util.List;

public class VerifyProductQuantityInCart_TC013 {

    private Home_page homePage;
    private Products_page productsPage;
    private Cart_page cartPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static Products[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: VerifyProductQuantityInCart_TC013 <<<<<<<");
        ListOfCredentials= Helpers.productsData("products");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: VerifyProductQuantityInCart_TC013 <<<<<<<");
    }

    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        homePage = new Home_page(driver);
        productsPage = new Products_page(driver);
        cartPage = new Cart_page(driver);
        System.out.println("<<<<< Before Test >>>>>");
    }

    /**
     * Tear down method runs after each test.
     * Quits the browser to clean up resources.
     */
    @AfterMethod
    public void tearDown() {
        driverFactory.close_browser();
        System.out.println("<<<<< After Test >>>>>");
    }

    /**
     * Data Provider returns array of User objects for all test users.
     * @return Products[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public Products[] userDataProvider(){
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify product quantity in cart is updated correctly")
    public void VerifyProductQuantityInCart(Products products) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl=Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl,homePageUrl,"home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //click on view first product
        //check user navigated to all the detail of the first product
        productsPage.clickOnViewFirstProduct();
        String actualDetailPageIfFirstProduct=Browser.getCurrent_URL();
        Assert.assertEquals(actualDetailPageIfFirstProduct,homePageUrl+"product_details/1",
                "first product url doesn't match the expected");

        //check first product details and list those not displayed
        List<String> missingDetails =productsPage.verifyFirstProductDetails();
        Assert.assertTrue(missingDetails.isEmpty(),"These product details are not displayed: "+missingDetails );
        productsPage.enterFirstProductQuantity(products.productQuantity);
        productsPage.clickOnAddToCartButton();

        //navigate to the view cart page and check the URL
        productsPage.clickOnViewCartButton();
        String actualViewCartUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualViewCartUrl, homePageUrl + "view_cart", "view cart url doesn't match the expected");
        System.out.println("view cart page is visible successfully");

        //Verify the product is displayed in cart page with exact quantity
        Assert.assertTrue(cartPage.verifyFirstProductDisplayInCart(),"first product is not displayed in cart");
        String actualQuantity =cartPage.verifyQuantityOfFirstProduct();
        Assert.assertEquals(actualQuantity,products.productQuantity,"not the same quantity");
    }
}
