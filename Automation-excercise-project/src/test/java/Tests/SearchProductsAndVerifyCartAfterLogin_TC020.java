package Tests;

import Browser.driverFactory;
import Pages.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.User_Data;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class SearchProductsAndVerifyCartAfterLogin_TC020 {

    private Home_page homePage;
    private Products_page productsPage;
    private Cart_page cartPage;
    private Login_Page loginPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static User_Data[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: SearchProductsAndVerifyCartAfterLogin_TC020 <<<<<<<");
        ListOfCredentials = Helpers.UserData("UserData");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: SearchProductsAndVerifyCartAfterLogin_TC020 <<<<<<<");
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
        loginPage = new Login_Page(driver);
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
     *
     * @return User_Data[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public User_Data[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify that products in the cart before login are the same after login  ")
    public void SearchProductsAndVerifyCartAfterLogin(User_Data userData) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");

        //click on products Header Button
        //navigate to the All Products page URL and check it
        homePage.clickOnProductsHeaderButton();
        String actualProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualProductsPageUrl, homePageUrl + "products",
                "products url doesn't match the expected");

        //check that all products list is visible on the products page
        productsPage.verifyProductListPage();
        System.out.println("The products list is visible");

        //search for a product
        productsPage.enterProductNameOnSearchField("saree");
        productsPage.clickOnSearchProductButton();

        //verify searched products text is visible
        //check user navigated to Searched Products page url
        String actualVisibleText = productsPage.verifySearchedProductsText();
        Assert.assertTrue(actualVisibleText.contains("SEARCHED PRODUCTS"));
        String actualSearchedProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualSearchedProductsPageUrl, homePageUrl + "products?search=saree",
                "'searched products' url doesn't match the expected");

        //verify all the products related to search are visible
        String actualVisibleName = productsPage.verifyNameOfSameProducts();
        Assert.assertTrue(actualVisibleName.contains("Saree"),
                "there are products unrelated to search are visible");

        //add the searched products to cart
        productsPage.clickOnAddToCartFromSearchedProducts();
        homePage.clickOnCartHeaderButton();

        //verify that products are visible in cart
        boolean actualFirstProductBeforeLogin = cartPage.verifyFirstSearchedProductsVisibleInCart();
        Assert.assertTrue(actualFirstProductBeforeLogin, "first product is not visible in the cart");
        boolean actualSecondProductBeforeLogin = cartPage.verifySecondSearchedProductsVisibleInCart();
        Assert.assertTrue(actualSecondProductBeforeLogin, "second product is not visible in the cart");
        boolean actualThirdProductBeforeLogin = cartPage.verifyThirdSearchedProductsVisibleInCart();
        Assert.assertTrue(actualThirdProductBeforeLogin, "third product is not visible in the cart");

        //navigate to the login page URL and check it
        homePage.clickOnLoginHeaderButton();
        String actualLoginUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualLoginUrl, homePageUrl + "login", "login url doesn't match the expected ");

        //enter login credentials
        loginPage.loginCredentials(userData.email, userData.password);
        loginPage.click_LoginButton();

        //click on cart button and Verify that those products are visible in cart after login as well
        homePage.clickOnCartHeaderButton();
        boolean actualFirstProductAfterLogin = cartPage.verifyFirstSearchedProductsVisibleInCart();
        Assert.assertTrue(actualFirstProductAfterLogin, "first product is not visible in the cart");
        boolean actualSecondProductAfterLogin = cartPage.verifySecondSearchedProductsVisibleInCart();
        Assert.assertTrue(actualSecondProductAfterLogin, "second product is not visible in the cart");
        boolean actualThirdProductAfterLogin = cartPage.verifyThirdSearchedProductsVisibleInCart();
        Assert.assertTrue(actualThirdProductAfterLogin, "third product is not visible in the cart");

    }
}
