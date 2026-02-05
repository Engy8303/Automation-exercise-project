package Tests;

import Pages.Home_page;
import Pages.Login_Page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.Invalid_Login;
import utils.Browser;
import Browser.driverFactory;
import utils.Helpers;

import java.io.FileNotFoundException;

public class InvalidLogin_TC003 {

    private Home_page homePage;
    private Login_Page loginPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static Invalid_Login[] ListOfInvalidLoginCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: InvalidLogin_TC003 <<<<<<<");
        ListOfInvalidLoginCredentials = Helpers.invalidLogin("InvalidLogin");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: InvalidLogin_TC003 <<<<<<<");
    }

    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        loginPage = new Login_Page(driver);
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
     * @return Invalid_Login[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public Invalid_Login[] userDataProvider() {
        return ListOfInvalidLoginCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify the user cannot login with invalid credentials and error message is displayed")
    public void InvalidLogin(Invalid_Login invalidLogin) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to the login page
        homePage.clickOnLoginHeaderButton();

        //verify 'Login to your account' text is visible
        String actualLoginText = loginPage.getLoginHeaderText();
        Assert.assertTrue(actualLoginText.contains("Login to your account"), "The text is not visible");
        System.out.println(" Login to your account is visible");

        //enter an invalid login credentials
        loginPage.loginCredentials(invalidLogin.email, invalidLogin.password);
        loginPage.click_LoginButton();

        //verify error message is visible
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains("Your email or password is incorrect!"),
                "The error message is not visible");
        System.out.println("Your email or password is incorrect! is visible");
    }
}
