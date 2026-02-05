package Tests;

import Browser.driverFactory;
import Pages.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.Register;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class VerifyAddressDetailsInCheckoutPage_TC023 {

    private Home_page homePage;
    private Signup_page signup;
    private Products_page productsPage;
    private Cart_page cartPage;
    private Checkout_page checkout_page;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static Register[] ListOfRegisterCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: VerifyAddressDetailsInCheckoutPage_TC023 <<<<<<<");
        ListOfRegisterCredentials = Helpers.registerUsers("Register");
    }

    //Runs once after all tests
    @AfterClass
    public void afterClass() {
        System.out.println(">>>>>>> After Class: VerifyAddressDetailsInCheckoutPage_TC023 <<<<<<<");
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
        productsPage = new Products_page(driver);
        cartPage = new Cart_page(driver);
        checkout_page = new Checkout_page(driver);
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
    @Description("verify address details are displayed correctly in checkout page")
    public void VerifyAddressDetailsInCheckout(Register register) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println(" home page is visible successfully");

        //enter new user credentials
        homePage.clickOnSignupHeaderButton();
        signup.enterNewUserSignupCredentials(register.username, register.email);
        signup.clickSignUpButton();

        //navigate to the signup page URL and check it
        String actualSignupUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualSignupUrl, homePageUrl + "signup",
                "signup page url doesn't match the expected");
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
        Assert.assertEquals(actualLoggedInMessage, "Logged in as " + register.name,
                "logged in as 'username' is not visible");
        System.out.println("'Logged in as username' is visible");

        //add first product to cart
        productsPage.addFirstProductOnCart();
        productsPage.clickOnContinueShoppingButton();

        //navigate to the cart page URL and check it
        homePage.clickOnCartHeaderButton();
        String actualCratPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualCratPageUrl, homePageUrl + "view_cart",
                "cart page url doesn't match the expected");
        cartPage.clickOnProceedToCheckoutButton();

        //Verify that the delivery address is same address filled at the time registration of account
        Assert.assertTrue(checkout_page.verifyDeliveryName().contains( register.title+" " +register.firstName+" "+register.lastName));
        Assert.assertTrue(checkout_page.verifyDeliveryCompany().contains(register.company));
        Assert.assertTrue(checkout_page.verifyDeliveryFirstAddress().contains(register.address));
        Assert.assertTrue(checkout_page.verifyDeliverySecondAddress().contains(register.address2));
        Assert.assertTrue(checkout_page.verifyDeliveryCity().contains(register.city +" "+register.state+" "+register.zipcode));
        Assert.assertTrue(checkout_page.verifyDeliveryCountry().contains(register.country));
        Assert.assertTrue(checkout_page.verifyDeliveryPhoneNumber().contains(register.phoneNumber));
        System.out.println("there are information in delivery address not same as the time of registration");

        //Verify that the billing address is same address filled at the time registration of account
        Assert.assertTrue(checkout_page.verifyBillingName().contains( register.title+" " +register.firstName+" "+register.lastName));
        Assert.assertTrue(checkout_page.verifyBillingCompany().contains(register.company));
        Assert.assertTrue(checkout_page.verifyBillingFirstAddress().contains(register.address));
        Assert.assertTrue(checkout_page.verifyBillingSecondAddress().contains(register.address2));
        Assert.assertTrue(checkout_page.verifyBillingCity().contains(register.city +" "+register.state+" "+register.zipcode));
        Assert.assertTrue(checkout_page.verifyBillingCountry().contains(register.country));
        Assert.assertTrue(checkout_page.verifyBillingPhoneNumber().contains(register.phoneNumber));
        System.out.println("there are information in billing address not same as the time of registration");

        //delete account and verify deletion
        homePage.clickOnDeleteAccountButton();
        String actualDeletedMessage = homePage.accountDeletedTextIsVisible();
        Assert.assertTrue(actualDeletedMessage.contains("Your account has been permanently deleted!"),
                "The deletion text is not visible");
        System.out.println("ACCOUNT DELETED! is visible");
        signup.clickContinueButton();
    }
}
