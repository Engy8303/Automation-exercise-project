package Tests;

import Browser.driverFactory;
import Pages.Home_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.User_Data;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class VerifySubscriptionInHomePage_TC010 {

    private Home_page homePage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static User_Data[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: VerifySubscriptionInHomePage_TC010 <<<<<<<");
        ListOfCredentials = Helpers.UserData("UserData");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: VerifySubscriptionInHomePage_TC010 <<<<<<<");
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

    /**
     * Data Provider returns array of User objects for all test users.
     * @return User_Data[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public User_Data[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify 'Subscription' text is visible in the home page successfully")
    public void VerifySubscriptionInHomePage(User_Data userdata) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //verify text 'SUBSCRIPTION' is visible at the bottom of the page
        String subscriptionTextActualResult = homePage.verifySubscriptionText();
        Assert.assertTrue(subscriptionTextActualResult.contains("SUBSCRIPTION"), "Subscription Text is not visible");
        System.out.println("'SUBSCRIPTION' text is visible ");

        //enter email and click arrow button
        homePage.enterSubscriptionEmail(userdata.email);
        homePage.clickSubscriptionArrowButton();
        System.out.println(" 'You have been successfully subscribed!' is visible");
    }
}
