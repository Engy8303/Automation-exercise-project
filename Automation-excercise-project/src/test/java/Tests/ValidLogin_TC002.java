package Tests;

import Pages.Home_page;
import Pages.Login_Page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.Valid_Login;
import utils.Browser;
import Browser.driverFactory;
import utils.Helpers;

import java.io.FileNotFoundException;

public class ValidLogin_TC002 {

    private Home_page homePage;
    private Login_Page loginPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static Valid_Login[] ListOfValidLoginCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: ValidLogin_TC002 <<<<<<<");
        ListOfValidLoginCredentials = Helpers.validLogin("ValidLogin");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: ValidLogin_TC002 <<<<<<<");
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
     * @return Valid_Login[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public Valid_Login[] userDataProvider() {
        return ListOfValidLoginCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify user can login with valid credentials successfully")
    public void ValidLogin(Valid_Login validLogin) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to the login page and check visibility text
        homePage.clickOnLoginHeaderButton();

        //verify 'Login to your account' text is visible
        String actualLoginText = loginPage.getLoginHeaderText();
        Assert.assertTrue(actualLoginText.contains("Login to your account"),
                "'login to your account' text is not visible");
        System.out.println(" Login to your account is visible");

        //enter valid credentials
        loginPage.loginCredentials(validLogin.email, validLogin.password);
        loginPage.click_LoginButton();

        //verify account created
        String actualLoggedInMessage = homePage.getSuccessMessage();
        Assert.assertEquals(actualLoggedInMessage, "Logged in as " + validLogin.name,
                "logged in as username is not visible ");
        System.out.println("'Logged in as username' is visible");

        //delete account and verify deletion
        homePage.clickOnDeleteAccountButton();
        String actualDeletedMessage = homePage.accountDeletedTextIsVisible();
        Assert.assertTrue(actualDeletedMessage.contains("Your account has been permanently deleted!"),
                "The deletion text is not visible");
        System.out.println("ACCOUNT DELETED! is visible");


    }

}
