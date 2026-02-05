package Tests;

import Browser.driverFactory;
import Pages.Home_page;
import Pages.Products_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Browser;


public class View_CartBrandProducts_TC019 {

    private Home_page homePage;
    private Products_page productsPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: ViewCartBrandProducts_TC019 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: ViewCartBrandProducts_TC019 <<<<<<<");
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

    @Test
    @Description("verify brands are visible in sidebar,user can navigate to brand pages and view products successfully")
    public void View_CartBrandProducts() {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //click on products Header Button and navigate to the All Products page URL and check it
        homePage.clickOnProductsHeaderButton();
        String actualProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualProductsPageUrl, homePageUrl + "products",
                "All Products url doesn't match the expected");
        System.out.println("user is navigated to ALL PRODUCTS page successfully");

        //verify that Brands are visible on left side
        boolean brandSectionVisible = productsPage.verifyBrandIsVisibleOnLeftSide();
        Assert.assertTrue(brandSectionVisible, "Brands are not visible on left side bar");
        System.out.println(" Brands are visible on left side of the page");

        //click on madame brand Button and navigate to madame page URL and check it
        //verify the products of that brand are displayed
        productsPage.clickOnMadameBrandButton();
        String actualMadameBrandUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualMadameBrandUrl, homePageUrl + "brand_products/Madame",
                "madame page url doesn't match the expected");
        boolean visibilityOfFirstBrandItems = productsPage.verifyBrandItemsIsVisible();
        Assert.assertTrue(visibilityOfFirstBrandItems, "madame brand products are not visible on the page");
        System.out.println("user is navigated to madame brand page and brand products are displayed");

        //click on kookie kids brand Button and navigate to kookie kids page URL and check it
        //verify the products of that brand are displayed
        productsPage.clickOnKookieKidsBrandButton();
        String actualKidsBrandUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualKidsBrandUrl, homePageUrl + "brand_products/Kookie%20Kids",
                "kookie kids page url doesn't match the expected");
        boolean visibilityOfSecondBrandItems = productsPage.verifyBrandItemsIsVisible();
        Assert.assertTrue(visibilityOfSecondBrandItems, "kookie kids brand products are not visible on the page");
        System.out.println("user is navigated to kookie kids brand page and brand products are displayed");

    }

}
