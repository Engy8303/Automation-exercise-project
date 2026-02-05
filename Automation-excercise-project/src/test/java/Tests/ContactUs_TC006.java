package Tests;

import Pages.ContactUs_Page;
import Pages.Home_page;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo_class.ContactUs;
import Browser.driverFactory;
import utils.Browser;
import utils.Helpers;

import java.io.FileNotFoundException;

public class ContactUs_TC006 {

    private Home_page homePage;
    private ContactUs_Page contactUsPage;
    private String homePageUrl = "https://automationexercise.com/";

    //Array holding user credentials loaded from JSON file
    static ContactUs[] ListOfCredentials;

    //Runs once before all tests
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        System.out.println(">>>>>>> Before Class: ContactUs_TC006 <<<<<<<");
        ListOfCredentials = Helpers.contactUsData("ContactUs");
    }

    //Runs once after all tests
    public void afterClass() {
        System.out.println(">>>>>>> After Class: ContactUs_TC006 <<<<<<<");
    }

    /**
     * Setup method runs before each test method.
     * Initializes the WebDriver and creates page object instances.
     */
    @BeforeMethod
    public void Setup() {
        WebDriver driver = driverFactory.getDriver("edge");
        homePage = new Home_page(driver);
        contactUsPage = new ContactUs_Page(driver);
    }

    /**
     * Tear down method runs after each test.
     * Quits the browser to clean up resources.
     */
    @AfterMethod
    public void tearDown() {
        driverFactory.close_browser();
    }

    /**
     * Data Provider returns array of User objects for all test users.
     * @return ContactUs[] objects loaded from JSON
     */
    @DataProvider(name = "userdata")
    public ContactUs[] userDataProvider() {
        return ListOfCredentials;
    }

    @Test(dataProvider = "userdata")
    @Description("Verify Contact Us form submission and success message is displayed successfully")
    public void ContactUsForm(ContactUs contactUs) {

        //navigate to the homepage URL and check it
        Browser.navigateToUrl(homePageUrl);
        String actualHomePageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualHomePageUrl, homePageUrl, "home page url doesn't match the expected");
        System.out.println("home page is visible successfully");

        //navigate to the ContactUs URL and check it
        homePage.clickContactUsHeaderButton();
        String actualContactUsPageUrl = Browser.getCurrent_URL();
        Assert.assertEquals(actualContactUsPageUrl, homePageUrl + "contact_us",
                "contactUs page url doesn't match the expected");

        //Verify 'GET IN TOUCH' is visible
        String getInTouchActualText = contactUsPage.verifyGetInTouchTextIsVisible();
        Assert.assertTrue(getInTouchActualText.contains("GET IN TOUCH"), "get in touch text is not visible");

        // enter name, email, subject , message and upload file
        contactUsPage.enterNameField(contactUs.name);
        contactUsPage.enterEmailField(contactUs.email);
        contactUsPage.enterSubjectField(contactUs.subject);
        contactUsPage.enterMessageField(contactUs.message);
        contactUsPage.uploadfile(contactUs.uploadFile);
        contactUsPage.clickSubmitButton();

        //Verify success message is visible
        String actualSuccessMessage = contactUsPage.verifySuccessMessageIsVisible();
        Assert.assertTrue(actualSuccessMessage.contains(" Your details have been submitted successfully"),
                "'Success! Your details have been submitted successfully' message is not visible");
        contactUsPage.clickOnHomeButton();

    }
}
