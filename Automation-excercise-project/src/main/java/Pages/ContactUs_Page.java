package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Browser;
import utils.my_framework;

public class ContactUs_Page {

   private my_framework framework;
   private WebDriver driver;

    //contact us page Locators
    private final By getInTouchTextLocator =By.cssSelector("div[class='contact-form'] h2");
    private final By nameFieldLocator=By.cssSelector("input[data-qa='name']");
    private final By emailFieldLocator=By.cssSelector("input[data-qa='email']");
    private final By subjectFieldLocator=By.cssSelector("input[data-qa='subject']");
    private final By messageFieldLocator=By.cssSelector("textarea[data-qa='message']");
    private final By chooseFileLocator= By.cssSelector("input[type='file']");
    private final By submitButtonLocator=By.cssSelector("input[type='submit']");
    private final By successMessageLocator=By.cssSelector("div[class='status alert alert-success']");
    private final By homeButtonLocator=By.cssSelector("a[class='btn btn-success']");

    //constructor for Contact Us page
    public ContactUs_Page(WebDriver driver){
        this.driver=driver;
        framework=new my_framework(driver);
    }

    //<<<< Methods of "ContactUs page" >>>>

    public String verifyGetInTouchTextIsVisible(){
        framework.scrollToElement(getInTouchTextLocator);
        return Browser.getText(getInTouchTextLocator);
    }

    public void enterNameField(String name){
        framework.scrollToElement(nameFieldLocator);
        framework.sendKeys(nameFieldLocator,name);
    }

    public void enterEmailField(String email){
        framework.scrollToElement(emailFieldLocator);
        framework.sendKeys(emailFieldLocator,email);
    }

    public void enterSubjectField(String subject){
        framework.scrollToElement(subjectFieldLocator);
        framework.sendKeys(subjectFieldLocator,subject);
    }

    public void enterMessageField(String message){
        framework.scrollToElement(messageFieldLocator);
        framework.sendKeys(messageFieldLocator,message);

    }

    public void uploadfile(String filePath){
        framework.scrollToElement(submitButtonLocator);
        framework.uploadFile(chooseFileLocator,filePath);
    }

    public void clickSubmitButton(){
        framework.scrollToElement(submitButtonLocator);
        framework.click(submitButtonLocator);
        framework.acceptAlert();
    }

    public String verifySuccessMessageIsVisible(){
        framework.scrollToElement(successMessageLocator);
        return Browser.getText(successMessageLocator);
    }

    public void clickOnHomeButton(){
        framework.scrollToElement(homeButtonLocator);
        framework.click(homeButtonLocator);
    }
}
