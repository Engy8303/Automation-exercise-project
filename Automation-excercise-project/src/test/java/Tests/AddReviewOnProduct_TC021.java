package Tests;

import Browser.driverFactory;
import Pages.Home_page;
import Pages.Products_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.User_Data;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class AddReviewOnProduct_TC021 {

    private Home_page homePage;
    private Products_page productsPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static User_Data[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: AddReviewOnProduct_TC021 <<<<<<<");
        ListOfCredentials = Helpers.UserData("UserData");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: AddReviewOnProduct_TC021 <<<<<<<");
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
     * @return User_Data[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public User_Data[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify user can add a review on product successfully")
    public void AddReviewOnProduct(User_Data userData) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //click on products Header Button and navigate to the All Products page URL and check it
        homePage.clickOnProductsHeaderButton();
        String actualProductsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualProductsPageUrl, homePageUrl + "products",
                "products url doesn't match the expected");
        System.out.println("user is navigated to ALL PRODUCTS page successfully");

        //check that all products list is visible on the products page
        String actualProductListText = productsPage.verifyProductListPage();
        Assert.assertTrue(actualProductListText.contains("ALL PRODUCTS"),
                "ALL PRODUCTS text is not visible");
        System.out.println("The products list is visible");

        //click on view first product and navigate to the all the detail of the first product URL and check it
        productsPage.clickOnViewFirstProduct();
        String actualDetailPageIfFirstProduct = Browser.getCurrent_URL();
        Assert.assertEquals(actualDetailPageIfFirstProduct, homePageUrl + "product_details/1",
                "first product details url doesn't match the expected");

        //Verify 'Write Your Review' is visible
        String WriteReviewProductActualText = productsPage.verifyWriteReviewProductText();
        Assert.assertTrue(WriteReviewProductActualText.contains("WRITE YOUR REVIEW"),
                "WRITE YOUR REVIEW text is not visible");

        //Enter name, email and review
        productsPage.fillOutReviewFieldsSection(userData.name, userData.email,
                "This blue top is elegantly designed and beautiful!");
        productsPage.clickSubmitReviewButton();

        //verify success message 'Thank you for your review.'
        String reviewActualSuccessMessage = productsPage.verifyReviewSuccessMessage();
        Assert.assertTrue(reviewActualSuccessMessage.contains("Thank you for your review"),
                "success message 'Thank you for your review' is not visible");

    }
}
