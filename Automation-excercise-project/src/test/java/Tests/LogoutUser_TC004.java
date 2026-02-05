package Tests;

import Pages.Home_page;
import Pages.Login_Page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.User_Data;
import Browser.driverFactory;
import utils.*;

import java.io.FileNotFoundException;

public class LogoutUser_TC004 {

    private Home_page homePage;
    private Login_Page loginPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static User_Data[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: LogoutUser_TC004 <<<<<<<");
        ListOfCredentials = Helpers.UserData("UserData");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: LogoutUser_TC004 <<<<<<<");
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
     *
     * @return User_Data[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public User_Data[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify the user can login with valid credentials and logout successfully")
    public void LogoutUser(User_Data userdata) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to the login page
        homePage.clickOnLoginHeaderButton();

        //verify 'Login to your account' text is visible
        String expectedLOginText = "Login to your account";
        String actualText = loginPage.getLoginHeaderText();
        Assert.assertTrue(actualText.contains(expectedLOginText), "The login text is not visible");
        System.out.println(expectedLOginText + " is visible");

        //enter valid login credentials
        loginPage.loginCredentials(userdata.email, userdata.password);
        loginPage.click_LoginButton();

        //verify success message is visible
        String actualSuccessMessage = homePage.getSuccessMessage();
        Assert.assertTrue(actualSuccessMessage.endsWith(userdata.name),
                "The message of logged in as username is not visible");
        System.out.println(" Logged in as " + userdata.name + " is visible");

        // Logout from the account and verify that the user is navigated back to the login page
        homePage.clickLogoutUser();
        String actualLoginPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualLoginPageUrl, homePageUrl + "login",
                "login url doesn't match the expected");
        System.out.println(("user is navigated to login page"));
    }

}
