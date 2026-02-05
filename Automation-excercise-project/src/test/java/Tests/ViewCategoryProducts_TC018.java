package Tests;

import Browser.driverFactory;
import Pages.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Browser;

public class ViewCategoryProducts_TC018 {

    private Home_page homePage;
    private String homePageUrl = "https://automationexercise.com/";

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: ViewCategoryProducts_TC018 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: ViewCategoryProducts_TC018 <<<<<<<");
    }

    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        homePage = new Home_page(driver);
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
    @Description("verify category products are displayed when selected successfully")
    public void ViewCategoryProducts() {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");

        //verify that categories are visible on left side of the page
        boolean visibilityOfCategory = homePage.verifyCategoriesVisibleOnLeftSide();
        Assert.assertTrue(visibilityOfCategory, "the category section is not displayed");

        //click on women button and verify that category page is displayed
        homePage.clickOnWomenButtonCategory();
        String visibilityOfText = homePage.verifyCategoryProductsText();
        Assert.assertTrue(visibilityOfText.contains("WOMEN - DRESS PRODUCTS"),
                "the women category product page is not displayed");

        //click on man button and verify that category page is displayed
        homePage.clickOnManButtonCategory();
        String visibilityOfText2 = homePage.verifyCategoryProductsText();
        Assert.assertTrue(visibilityOfText2.contains("MEN - JEANS PRODUCTS"),
                "the men category product page is not displayed");

    }
}
