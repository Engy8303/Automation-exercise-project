package Tests;

import Pages.Home_page;
import Pages.Products_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import Browser.driverFactory;
import utils.Browser;

import java.util.List;

public class VerifyAllProductsAndProductDetailPage_TC008  {

    private Home_page homePage;
    private Products_page productsPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: VerifyAllProductsAndProductDetailPage_TC008 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: VerifyAllProductsAndProductDetailPage_TC008 <<<<<<<");
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
    @Description("verify all products are listed and product detail page shows complete information")
    public void VerifyAllProductsAndProductDetailPage() {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //click on products Header Button and navigate to the All Products page URL and check it
        homePage.clickOnProductsHeaderButton();
        String actualProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualProductsPageUrl, homePageUrl + "products", "products url doesn't match the expected");
        System.out.println("user is navigated to ALL PRODUCTS page successfully");

        //check that all products list is visible on the products page
        String actualProductListText = productsPage.verifyProductListPage();
        Assert.assertTrue(actualProductListText.contains("ALL PRODUCTS"), "ALL PRODUCTS text is not visible");
        System.out.println("The products list is visible");

        //click on view first product and navigate to the all the detail of the first product URL and check it
        productsPage.clickOnViewFirstProduct();
        String actualDetailPageIfFirstProduct = Browser.getCurrent_URL();
        Assert.assertEquals(actualDetailPageIfFirstProduct, homePageUrl + "product_details/1",
                "first product details url doesn't match the expected");

        //get all the details of the first product that not displayed
        List<String> missingDetails = productsPage.verifyFirstProductDetails();
        Assert.assertTrue(missingDetails.isEmpty(), "These product details are not displayed: " + missingDetails);
    }
}
