package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class waits {
    private WebDriver driver;

    public  waits(WebDriver driver){
        this.driver=driver;
    }

    public void implicitWait( int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public WebElement Waitfor_visibleElement(By locator, int timeoutSeconds){
        return new WebDriverWait(driver,Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement Waitfor_clickableElement(By locator, int timeoutSeconds){
        return new WebDriverWait(driver,Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public Alert Waitfor_Alert(int timeoutSeconds){
        return new WebDriverWait(driver,Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.alertIsPresent());
    }

    public WebElement Waitfor_PresenceElement(By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean Waitfor_Url(String url, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.urlContains(url));
    }
}
