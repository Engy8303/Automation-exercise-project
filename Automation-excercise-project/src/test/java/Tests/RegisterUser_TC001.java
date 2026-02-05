package Tests;

import Pages.Home_page;
import Pages.Signup_page;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.Register;
import utils.*;
import Browser.driverFactory;
import java.io.FileNotFoundException;

public class RegisterUser_TC001 {

    private Home_page homePage;
    private Signup_page signup;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static Register[] ListOfRegisterCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: RegisterUser_TC001 <<<<<<<");
        ListOfRegisterCredentials = Helpers.registerUsers("Register");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: RegisterUser_TC001 <<<<<<<");
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
     * @return Register[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public Register[] userDataProvider() {
        return ListOfRegisterCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify user can create an account and delete it successfully")
    public void RegisterUser(Register register) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //click signup button and check text is visible
        homePage.clickOnSignupHeaderButton();
        String expectedNewUserText = "New User Signup!";
        String actualNewUserText = signup.newUserSignUpTextIsVisible();
        Assert.assertTrue(actualNewUserText.contains(expectedNewUserText),
                "The new user signup text is not visible");
        System.out.println("'New User Signup!' is visible ");

        //enter new user credentials
        signup.enterNewUserSignupCredentials(register.username, register.email);
        signup.clickSignUpButton();

        //check the user navigated to the signup page
        String expectedSignupUrl = "https://automationexercise.com/signup";
        String actualSignupUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualSignupUrl, expectedSignupUrl, "signup page url doesn't match the expected");
        System.out.println("'ENTER ACCOUNT INFORMATION' is visible successfully");

        //enter new user account information
        signup.clickMrsRadioButton();
        signup.enterSignupName(register.name);
        signup.enterPassword(register.password);
        signup.selectDayOfBirthByValue(register.dayOfBirth);
        signup.selectMonthByValue(register.month);
        signup.selectYearByValue(register.year);
        signup.clickSignupCheckBox();
        signup.clickSpecialOffersCheckBox();

        //enter new user address information
        signup.enterFirstName(register.firstName);
        signup.enterLastName(register.lastName);
        signup.enterCompany(register.company);
        signup.enterAddress(register.address);
        signup.enterAddress2(register.address2);
        signup.selectCountryByValue(register.country);
        signup.enterState(register.state);
        signup.enterCity(register.city);
        signup.enterZipCode(register.zipcode);
        signup.enterMobileNumber(register.phoneNumber);
        signup.clickCreateAccountButton();

        //create account
        String actualAccountCreated = signup.accountCreatedTextIsVisible();
        Assert.assertTrue(actualAccountCreated.contains("Your new account has been successfully created!"),
                "The account created text is not visible");
        System.out.println("ACCOUNT CREATED! is visible");
        signup.clickContinueButton();

        //verify account created
        String actualLoggedInMessage = homePage.getSuccessMessage();
        Assert.assertEquals(actualLoggedInMessage, "Logged in as " + register.name);
        System.out.println("'Logged in as username' is visible");

        //delete account and verify deletion
        homePage.clickOnDeleteAccountButton();
        String actualDeletedMessage = homePage.accountDeletedTextIsVisible();
        Assert.assertTrue(actualDeletedMessage.contains("Your account has been permanently deleted!"),
                "The deletion text is not visible");
        System.out.println("ACCOUNT DELETED! is visible");
        signup.clickContinueButton();
    }

}
