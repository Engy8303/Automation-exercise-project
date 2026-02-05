package Tests;

import Pages.Home_page;
import Pages.Products_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import Browser.driverFactory;
import pojo_class.Products;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class Search_Product_TC009 {

    private Home_page homePage;
    private Products_page productsPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static Products[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: Search_Product_TC009 <<<<<<<");
        ListOfCredentials = Helpers.productsData("products");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: Search_Product_TC009 <<<<<<<");
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
    public Products[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify user can search any product and the related products are display successfully")
    public void Search_Product(Products products) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");

        //click on products Header Button
        //navigate to the All Products page URL and check it
        homePage.clickOnProductsHeaderButton();
        String actualProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualProductsPageUrl, homePageUrl + "products",
                "product url doesn't match the expected");

        //check that all products list is visible on the products page
        productsPage.verifyProductListPage();
        System.out.println("The products list is visible");

        //search for a product
        productsPage.enterProductNameOnSearchField(products.searchProduct);
        productsPage.clickOnSearchProductButton();

        //verify searched products text is visible
        //check user navigated to Searched Products page url
        productsPage.verifySearchedProductsText();
        String actualSearchedProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualSearchedProductsPageUrl, homePageUrl + "products?search=dress",
                "url doesn't match the expected");

    }
}
