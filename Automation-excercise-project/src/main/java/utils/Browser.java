package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;


public class Browser {

      private static WebDriver driver;
      private static my_framework framework;
      private static waits waits;


    //initialize the browser
    public static WebDriver initializeBrowser(WebDriver webDriver) {
        driver = webDriver;
        framework = new my_framework(driver);
        waits=new waits(driver);
        return webDriver;
    }


    //Navigate to the specified Url in the browser
    public static void navigateToUrl(String url){
        driver.get(url);
    }

    // wait until the URL to be visible and then print it
    public static void print_CurrentURL(){
        String url=framework.getCurrentURL();
        System.out.println("the current url is : "+ url);
    }

    // return the current Url
    public static String getCurrent_URL(){
        waits.Waitfor_Url(framework.getCurrentURL(),20);
        return framework.getCurrentURL();
    }

    public static String getText(By locator){
         return framework.getText(locator);
    }

}
