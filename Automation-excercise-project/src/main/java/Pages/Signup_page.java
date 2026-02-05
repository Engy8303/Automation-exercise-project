package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pojo_class.Register;
import utils.Browser;
import utils.my_framework;
import utils.waits;

public class Signup_page {

    private my_framework framework;
    private WebDriver driver;

    // <<<<< Locators of Signup Page >>>>>

    //New User Signup Locators
    private final By newUserSignupTextLocator = By.cssSelector("div.signup-form >h2");
    private final By newUserSignupNameLocator = By.cssSelector("[data-qa='signup-name']");
    private final By newUserSignupEmailAddressLocator = By.cssSelector("[data-qa='signup-email']");
    private final By signupButtonLocator = By.cssSelector("button[data-qa='signup-button']");
    private final By existEmailErrorMessageLocator = By.cssSelector("p[style='color: red;']");

    //Enter Account Information Locators
    private final By enterAccountInfoTextLocator = By.cssSelector("div.login-form >h2");
    private final By radioButtonMrLocator = By.cssSelector("#id_gender1");
    private final By radioButtonMrsLocator = By.cssSelector("#id_gender2");
    private final By nameLocator = By.cssSelector("#name");
    private final By passwordLocator = By.cssSelector("#password");
    private final By dayDropDownLocator = By.cssSelector("#days");
    private final By monthDropDownLocator = By.cssSelector("#months");
    private final By yearDropDownLocator = By.cssSelector("#years");
    private final By firstCheckBoxLocator = By.cssSelector("#newsletter");
    private final By secondCheckBoxLocator = By.cssSelector("#optin");

    //Address Information Locators
    private final By firstNameLocator = By.cssSelector("#first_name");
    private final By lastNameLocator = By.cssSelector("#last_name");
    private final By companyLocator = By.cssSelector("#company");
    private final By addressLocator = By.cssSelector("#address1");
    private final By address2Locator = By.cssSelector("#address2");
    private final By countryLocator = By.cssSelector("#country");
    private final By stateLocator = By.cssSelector("#state");
    private final By cityLocator = By.cssSelector("#city");
    private final By zipCodeLocator = By.cssSelector("#zipcode");
    private final By mobileNumberLocator = By.cssSelector("#mobile_number");
    private final By createAccountButtonLocator = By.cssSelector("button[data-qa='create-account']");


    //Created Account Locators
    private final By  accountCreatedTextLocator=By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p:nth-of-type(1)");
    private final By continueButtonLocator = By.cssSelector("a[data-qa='continue-button']");

    //constructor for Signup_page
    public Signup_page(WebDriver driver) {
        this.driver = driver;
        framework = new my_framework(driver);
    }

    // <<<<<< Methods Of Signup >>>>>>>

    // Methods to check text visibility and printing
    public String newUserSignUpTextIsVisible() {
        return Browser.getText(newUserSignupTextLocator);
    }

    public String newUserSignUpTextVisible() {
        return Browser.getText(newUserSignupTextLocator);
    }

    public String enterAccountInfoTextIsVisible() {
        return Browser.getText(enterAccountInfoTextLocator);
    }

    public String accountCreatedTextIsVisible(){
       return Browser.getText(accountCreatedTextLocator);
    }

    // <<<< Methods of "New User Signup" >>>>

    public void enterNewUserSignupCredentials(String name, String email) {
        framework.sendKeys(newUserSignupNameLocator, name);
        framework.sendKeys(newUserSignupEmailAddressLocator, email);
    }

    public void clickSignUpButton() {
        framework.scrollToElement(signupButtonLocator);
        framework.click(signupButtonLocator);
    }

    public String existEmailErrorMessage(){
        return Browser.getText(existEmailErrorMessageLocator);
    }


    // <<<< Methods of section "Enter Account Information" >>>>

    public void clickMrRadioButton() {
        framework.checkCheckbox(radioButtonMrLocator);
    }

    public void clickMrsRadioButton() {
        framework.checkCheckbox(radioButtonMrsLocator);
    }

    public void enterSignupName(String name) {
        framework.scrollToElement(nameLocator);
        framework.clear(nameLocator);
        framework.sendKeys(nameLocator, name);
    }

    public void enterPassword(String password) {
        framework.scrollToElement(passwordLocator);
        framework.sendKeys(passwordLocator, password);
    }

    public void selectDayOfBirthByValue(String days) {
        framework.scrollToElement(dayDropDownLocator);
        framework.selectDropdownByValue(dayDropDownLocator, days);
    }

    public void selectDaysOfBirthByIndex(int days) {
        framework.scrollToElement(dayDropDownLocator);
        framework.selectDropdownByIndex(dayDropDownLocator, days);
    }

    public void selectMonthByValue(String month) {
        framework.scrollToElement(monthDropDownLocator);
        framework.selectDropdownByValue(monthDropDownLocator, month);
    }

    public void selectMonthByIndex(int month) {
        framework.scrollToElement(monthDropDownLocator);
        framework.selectDropdownByIndex(monthDropDownLocator, month);
    }

    public void selectYearByValue(String year) {
        framework.scrollToElement(yearDropDownLocator);
        framework.selectDropdownByValue(yearDropDownLocator, year);
    }

    public void selectYearByIndex(int year) {
        framework.scrollToElement(yearDropDownLocator);
        framework.selectDropdownByIndex(yearDropDownLocator, year);
    }

    public void clickSignupCheckBox() {
        framework.scrollToElement(firstNameLocator);
        framework.click(firstCheckBoxLocator);
        System.out.println(framework.selectElement(firstCheckBoxLocator));
    }

    public void clickSpecialOffersCheckBox() {
        framework.scrollToElement(firstNameLocator);
        framework.click(secondCheckBoxLocator);
        System.out.println(framework.selectElement(secondCheckBoxLocator));
    }

    // <<<< Methods of section "Enter Address Information" >>>>

    public void enterFirstName(String firstname) {
        framework.scrollToElement(firstNameLocator);
        framework.sendKeys(firstNameLocator, firstname);
    }

    public void enterLastName(String lastname) {
        framework.scrollToElement(lastNameLocator);
        framework.sendKeys(lastNameLocator, lastname);
    }

    public void enterCompany(String company) {
        framework.scrollToElement(companyLocator);
        framework.sendKeys(companyLocator, company);

    }

    public void enterAddress(String address) {
        framework.scrollToElement(addressLocator);
        framework.sendKeys(addressLocator, address);
    }

    public void enterAddress2(String address2) {
        framework.scrollToElement(countryLocator);
        framework.sendKeys(address2Locator, address2);

    }

    public void selectCountryByValue(String country) {
        framework.scrollToElement(stateLocator);
        framework.selectDropdownByValue(countryLocator, country);
    }

    public void enterState(String state) {
        framework.scrollToElement(cityLocator);
        framework.sendKeys(stateLocator, state);
    }

    public void enterCity(String city) {
        framework.scrollToElement(zipCodeLocator);
        framework.sendKeys(cityLocator, city);
    }

    public void enterZipCode(String zipcode) {
        framework.scrollToElement(mobileNumberLocator);
        framework.sendKeys(zipCodeLocator, zipcode);
    }

    public void enterMobileNumber(String number) {
        framework.scrollToElement(mobileNumberLocator);
        framework.sendKeys(mobileNumberLocator, number);
    }

    public void clickCreateAccountButton() {
        framework.scrollToElement(createAccountButtonLocator);
        framework.click(createAccountButtonLocator);
    }

    public void clickContinueButton(){
        framework.scrollToElement(continueButtonLocator);
        framework.click(continueButtonLocator);
    }
}
