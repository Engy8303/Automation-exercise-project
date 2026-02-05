package Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v140.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.Browser;
import utils.my_framework;
import utils.waits;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class driverFactory  {

  private static WebDriver driver;
  private static my_framework framework;


    /*
    Returns a WebDriver for Chrome or Edge
     with maximized window, disabled notifications,
    and ad-block enabled
    */

    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized"); // open full screen
                chromeOptions.addArguments("--disable-notifications"); // block site notifications

                driver = new ChromeDriver(chromeOptions);
                DevTools devTools = ((ChromeDriver) driver).getDevTools();
                devTools.createSession();
                enableAdBlock(devTools);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized"); // open full screen
                edgeOptions.addArguments("--disable-notifications"); // block site notifications

                driver = new EdgeDriver(edgeOptions);
                DevTools dev_Tools = ((EdgeDriver) driver).getDevTools();
                dev_Tools.createSession();
                enableAdBlock(dev_Tools);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        Browser.initializeBrowser(driver);
        return driver;
    }


    //<<<<< Blocks common ads and tracking URLs >>>>>
    public static void enableAdBlock(DevTools devTools) {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(Arrays.asList(
                "https://*.doubleclick.net/*",
                "https://*.googlesyndication.com/*",
                "https://*.adservice.google.com/*",
                "https://*.facebook.com/ads/*",
                "https://*.taboola.com/*",
                "https://*.outbrain.com/*"
        )));
    }

    //Quit and close all the tabs on the browser
    public static void close_browser() {
        if (driver != null)
            driver.quit();
    }

}

