package Tests;

import Pages.Home_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import Browser.driverFactory;
import utils.Browser;


public class TestCasesPage_TC007 {

    private Home_page homePage;
    private String homePageUrl = "https://automationexercise.com/";


    //Runs once before all tests
    @BeforeClass
    public void beforeClass() {
        System.out.println(">>>>>>> Before Class: TestCasesPage_TC007 <<<<<<<");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: TestCasesPage_TC007 <<<<<<<");
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
    public void teardown() {
        driverFactory.close_browser();
        System.out.println("<<<<< After Test >>>>>");
    }

    @Test
    @Description("verify user can navigate to the TestCases page and the test cases displayed successfully  ")
    public void verifyTestCasePage() throws InterruptedException {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to the TestCases page url and check it
        homePage.clickOnTestCasesHeaderButton();
        String actualTestCaseUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualTestCaseUrl, homePageUrl + "test_cases", "url doesn't match the expected");
        System.out.println("user is navigated to test cases page successfully");
        Thread.sleep(1000);
    }
}
