package utils;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.List;
import java.util.Set;

public class my_framework {


    private WebDriver driver;
    private waits waits;

    public my_framework(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
        waits = new waits(driver);
    }

    public void navigateToURL(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void click(By locator) {
        WebElement element = waits.Waitfor_clickableElement(locator, 20);
        element.click();
    }

    public boolean displayElement(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public boolean selectElement(By locator) {
        return driver.findElement(locator).isSelected();
    }

    public void rightClick(By locator) {
        WebElement element = waits.Waitfor_visibleElement(locator, 20);
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
    }

    public void sendKeys(By locator, String text) {
        waits.Waitfor_visibleElement(locator, 20);
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        waits.Waitfor_visibleElement(locator, 20);
        return driver.findElement(locator).getText();
    }

    public void scrollToElement(By locator) {
        WebElement element = waits.Waitfor_visibleElement(locator, 20);
        Actions action = new Actions(driver);
        action.scrollToElement(element).perform();
    }

    public void moveToElement(By locator) {
        WebElement element = waits.Waitfor_visibleElement(locator, 20);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void checkCheckbox(By locator) {
        WebElement checkbox = waits.Waitfor_clickableElement(locator, 20);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void UncheckCheckbox(By locator) {
        WebElement checkbox = waits.Waitfor_clickableElement(locator, 20);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectRadioButton(By locator) {
        WebElement RadioButton = waits.Waitfor_clickableElement(locator, 20);
        if (!RadioButton.isSelected()) {
            RadioButton.click();
        }
    }

    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = waits.Waitfor_visibleElement(locator, 20);
        new Select(dropdown).selectByVisibleText(visibleText);
    }

    public void selectDropdownByValue(By locator, String value) {
        WebElement dropdown = waits.Waitfor_visibleElement(locator, 20);
        new Select(dropdown).selectByValue(value);
    }

    public void selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = waits.Waitfor_visibleElement(locator, 20);
        new Select(dropdown).selectByIndex(index);
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waits.Waitfor_visibleElement(sourceLocator, 20);
        WebElement target = waits.Waitfor_visibleElement(targetLocator, 20);
        Actions action = new Actions(driver);
        action.dragAndDrop(source, target).perform();
    }

    public void switchToWindowByTitle(String windowTitle) {
        String currentwindow = driver.getWindowHandle();
        Set<String> allwindows = driver.getWindowHandles();
        for (String window : allwindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(windowTitle)) {
                return;
            }
        }
        driver.switchTo().window(currentwindow);
        System.out.println("window title " + windowTitle + " not found");
    }

    public void switchToWindowByHandle(String windowHandle) {
        Set<String> allwindows = driver.getWindowHandles();
        if (allwindows.contains(windowHandle)) {
            driver.switchTo().window(windowHandle);
        } else {
            System.out.println("window handle " + windowHandle + " not found");
        }
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void acceptAlert() {
        waits.Waitfor_Alert(20).accept();
    }

    public void dismissAlert() {
        waits.Waitfor_Alert(20);
        driver.switchTo().alert().dismiss();

    }

    public String getAlertText() {
        return waits.Waitfor_Alert(20).getText();
    }

    public void sendTextToAlert(String text) {
        waits.Waitfor_Alert(20).sendKeys(text);
    }

    // wait until the URL is visible
    public String getUrl(String url) {
        waits.Waitfor_Url(url, 20);
        return url;
    }

    //wait until an element text is display on the screen
    public void visibleText(By locator) {
        waits.Waitfor_visibleElement(locator, 20).isDisplayed();
    }

    //wait until an element text is display on the screen and then print that text
    public void printVisibleText(By locator) {
        waits.Waitfor_visibleElement(locator, 20).isDisplayed();
        String text = driver.findElement(locator).getText();
        System.out.println("the visible text is : " + text);
    }

    //
    public void DropDown(By locator, String text) {
        WebElement dropdown = driver.findElement(locator);
        Select select = new Select(dropdown);
        select.selectByValue(text);
    }

    public void iframe(WebElement element) {
        driver.switchTo().frame(element);
        driver.switchTo().defaultContent();
    }

    public void clear(By locator) {
        driver.findElement(locator).clear();
    }

    public void uploadFile(By locator, String filepath) {
        waits.Waitfor_visibleElement(locator, 20);
        driver.findElement(locator).sendKeys(filepath);
    }

    public void checkAndAddIfNotDisplayed(By locator,String message, List<String> notDisplayed) {
        if (!displayElement(locator)) {
            notDisplayed.add( message );
        }
    }

}
