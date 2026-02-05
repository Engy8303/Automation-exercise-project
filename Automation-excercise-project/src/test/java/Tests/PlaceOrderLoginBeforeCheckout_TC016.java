package Tests;

import Browser.driverFactory;
import Pages.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.LoginBeforeCheckout;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class PlaceOrderLoginBeforeCheckout_TC016 {

    private Home_page homePage;
    private Login_Page loginPage;
    private Signup_page signup;
    private Products_page productsPage;
    private Cart_page cartPage;
    private Checkout_page checkout_page;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static LoginBeforeCheckout[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: PlaceOrderLoginBeforeCheckout_TC016 <<<<<<<");
        ListOfCredentials = Helpers.loginBeforeCheckout("LoginBeforeCheckout");
    }

    //Runs once after all tests
    @AfterClass
    public void afterclass() {
        System.out.println(">>>>>>> After Class: PlaceOrderLoginBeforeCheckout_TC016 <<<<<<<");
    }

    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        loginPage = new Login_Page(driver);
        signup = new Signup_page(driver);
        homePage = new Home_page(driver);
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
     * @return LoginBeforeCheckout[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public LoginBeforeCheckout[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("verify user can login before checkout and place order successfully")
    public void PlaceOrderLoginBeforeCheckout(LoginBeforeCheckout loginBeforeCheckout) {

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
        loginPage.loginCredentials(loginBeforeCheckout.email, loginBeforeCheckout.password);
        loginPage.click_LoginButton();

        //verify success message is visible
        String actualSuccessMessage = homePage.getSuccessMessage();
        Assert.assertTrue(actualSuccessMessage.endsWith(loginBeforeCheckout.name),
                "The message of logged in as username is not visible");
        System.out.println(" Logged in as " + loginBeforeCheckout.name + " is visible");

        //add first product to cart
        productsPage.addFirstProductOnCart();
        productsPage.clickOnContinueShoppingButton();

        //navigate to the cart page URL and check it
        homePage.clickOnCartHeaderButton();
        String actualCratPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualCratPageUrl, homePageUrl + "view_cart",
                "cart page url doesn't match the expected");
        cartPage.clickOnProceedToCheckoutButton();

        //verify address details and review your order is visible
        boolean actualResult = checkout_page.verifyAddressDetails_reviewOrderIsVisible();
        Assert.assertTrue(actualResult, "Address Details and Review Your Order are not displayed");

        //enter description in comment text area and click 'Place Order'
        checkout_page.enterCommentOnOrder("Please pack the items securely Thank You!");
        checkout_page.clickOnPlaceOrderButton();

        //enter the payment fields information
        checkout_page.enterPaymentFields(loginBeforeCheckout.nameOnCard, loginBeforeCheckout.cardNumber,
                loginBeforeCheckout.cvc, loginBeforeCheckout.ExpirationMonth, loginBeforeCheckout.ExpirationYear);
        checkout_page.clickOnPayAndConfirmButton();

        //verify success message 'Your order has been placed successfully!'
        String actualSuccessMsg = checkout_page.verifyOrderSuccessMessageText();
        Assert.assertTrue(actualSuccessMsg.contains("Congratulations!"),
                "the success message of order placed successfully is not displayed ");

        //delete account and verify deletion
        homePage.clickOnDeleteAccountButton();
        String actualDeletedMessage=homePage.accountDeletedTextIsVisible();
        Assert.assertTrue(actualDeletedMessage.contains("Your account has been permanently deleted!"),
                "The deletion text is not visible");
        System.out.println("ACCOUNT DELETED! is visible");
        signup.clickContinueButton();

    }
}
