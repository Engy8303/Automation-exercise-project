package Tests;

import Browser.driverFactory;
import Pages.Cart_page;
import Pages.Home_page;
import Pages.Products_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Browser;


public class AddProductsInCart_TC012 {

    private Home_page homePage;
    private Products_page productsPage;
    private Cart_page cartPage;
    private String homePageUrl = "https://automationexercise.com/";


    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: AddProductsInCart_TC012 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: AddProductsInCart_TC012 <<<<<<<");
    }

    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        productsPage = new Products_page(driver);
        homePage = new Home_page(driver);
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

    @Test
    @Description("verify the user can add products in the cart successfully")
    public void addProductsInCart() {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to the products page by clicking the header products button
        homePage.clickOnProductsHeaderButton();

        //add the first product to cart
        productsPage.addFirstProductOnCart();
        productsPage.clickOnContinueShoppingButton();

        //add the second product to cart
        productsPage.addSecondProductOnCart();

        //navigate to the view cart page and check the URL
        productsPage.clickOnViewCartButton();
        String actualViewCartUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualViewCartUrl, homePageUrl + "view_cart", "view cart url doesn't match the expected");
        System.out.println("view cart page is visible successfully");

        //Verify products are added to Cart
        boolean actualDisplayFirstProduct = cartPage.verifyFirstProductDisplayInCart();
        Assert.assertTrue(actualDisplayFirstProduct, "first product is not displayed");
        boolean actualDisplaySecondProduct = cartPage.verifySecondProductDisplayInCart();
        Assert.assertTrue(actualDisplaySecondProduct, "second product is not displayed");
        System.out.println("both products are added to Cart successfully ");

        //Verify products prices, quantity and total price are visible
        cartPage.verifyFirstProductInfoInCart();
        cartPage.verifySecondProductInfoInCart();
    }
}
