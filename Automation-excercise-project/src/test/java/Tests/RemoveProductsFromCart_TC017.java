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


public class RemoveProductsFromCart_TC017 {

    private Home_page homePage;
    private Products_page productsPage;
    private Cart_page cartPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: RemoveProductsFromCart_TC017 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: RemoveProductsFromCart_TC017 <<<<<<<");
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
    @Description("verify user can remove products from cart successfully")
    public void RemoveProductsFromCart() throws InterruptedException {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");

        //add product in cart
        productsPage.addFirstProductOnCart();
        productsPage.clickOnContinueShoppingButton();

        //navigate to the cart page URL and check it
        homePage.clickOnCartHeaderButton();
        String actualCartPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualCartPageUrl, homePageUrl + "view_cart", "cart page url doesn't match the expected");

        //verify product removed from cart
        cartPage.clickOnXButton();
        Thread.sleep(2000);
        boolean actualDeletedProductResult = cartPage.verifyDeletionOfProductFromCart();
        Assert.assertTrue(actualDeletedProductResult, "product still on cart");
    }

}
