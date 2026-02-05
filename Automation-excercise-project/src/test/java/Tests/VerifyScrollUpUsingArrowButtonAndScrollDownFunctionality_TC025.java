package Tests;

import Browser.driverFactory;
import Pages.Home_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Browser;

public class VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality_TC025 {

    private Home_page homePage;
    private String homePageUrl = "https://automationexercise.com/";


    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality_TC025 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality_TC025 <<<<<<<");
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
    @Description("verify scroll up using 'Arrow' button and scroll down functionality works successfully")
    public void VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality() throws InterruptedException {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //verify text 'SUBSCRIPTION' is visible at the bottom of the page
        String subscriptionTextActualResult = homePage.verifySubscriptionText();
        Assert.assertTrue(subscriptionTextActualResult.contains("SUBSCRIPTION"), "Subscription Text is not visible");
        System.out.println("'SUBSCRIPTION' text is visible ");

        //verify that page is scrolled up using arrow and the header text on homepage is visible on screen
        homePage.clickOnScrollUpArrow();
        Thread.sleep(1000);
        String actualHeaderText = homePage.verifyHeaderTextOnHomepageIsVisible();
        Assert.assertTrue(actualHeaderText.contains("Full-Fledged practice"),
                "'Full-Fledged practice website for Automation Engineers' is not visible");
    }
}
