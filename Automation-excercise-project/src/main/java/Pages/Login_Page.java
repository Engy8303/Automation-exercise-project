package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import utils.my_framework;

public class Login_Page {

    private my_framework framework;
    private WebDriver driver;

    //<<<<<<<< Locators of Login >>>>>>>>>
    private final By loginToYourAccountTextLocator=By.cssSelector("div.login-form h2");
    private final By emailAddressLocator=By.cssSelector("input[data-qa='login-email']");
    private final By passwordLocator=By.cssSelector("input[data-qa='login-password']");
    private final By loginButtonLocator=By.cssSelector("[data-qa='login-button']");
    private final By errorMessageLocator =By.cssSelector("p[style='color: red;']");


    //constructor for Login_Page
    public Login_Page(WebDriver driver) {
        this.driver=driver;
        framework=new my_framework(driver);
    }

    //<<<< Methods of "Login_Page" >>>>

    public String getLoginHeaderText(){
        framework.scrollToElement(loginToYourAccountTextLocator);
        return Browser.getText(loginToYourAccountTextLocator);
    }
    public void loginCredentials(String Email, String password){
        framework.scrollToElement(emailAddressLocator);
        framework.sendKeys(emailAddressLocator,Email);
        framework.sendKeys(passwordLocator,password);
    }

    public void click_LoginButton() {
        framework.scrollToElement(loginButtonLocator);
        framework.click(loginButtonLocator);
    }

    //Read and print error message for invalid credentials
    public String getErrorMessage(){
          return Browser.getText(errorMessageLocator);
    }

}
