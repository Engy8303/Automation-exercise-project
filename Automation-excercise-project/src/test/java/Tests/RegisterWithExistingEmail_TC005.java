package Tests;

import Pages.Home_page;
import Pages.Signup_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.User_Data;
import utils.Browser;
import Browser.driverFactory;
import utils.Helpers;

import java.io.FileNotFoundException;

public class RegisterWithExistingEmail_TC005 {

    private Home_page homePage;
    private Signup_page signup;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static User_Data[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: RegisterUserWithExistingEmail_TC005 <<<<<<<");
        ListOfCredentials = Helpers.UserData("UserData");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: RegisterUserWithExistingEmail_TC005 <<<<<<<");
    }


    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        homePage = new Home_page(driver);
        signup = new Signup_page(driver);
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
    @Description("Verify registration fails when using an existing email")
    public void RegisterWithExistingEmail(User_Data userdata) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String expectedHomePageUrl = homePageUrl;
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, expectedHomePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to signup page and verify visibility of text
        homePage.clickOnSignupHeaderButton();
        String actualNewUserText = signup.newUserSignUpTextIsVisible();
        Assert.assertTrue(actualNewUserText.contains("New User Signup!"), "New User Signup! text is not visible");
        System.out.println("'New User Signup!' is visible ");

        //enter new user credentials
        signup.enterNewUserSignupCredentials(userdata.name, userdata.email);
        signup.clickSignUpButton();

        //check email already exist message
        String actualEmailExistMessage = signup.existEmailErrorMessage();
        Assert.assertTrue(actualEmailExistMessage.contains("Email Address already exist!"), "The message is not visible");
        System.out.println("'Email Address already exist!' is visible");
    }
}
