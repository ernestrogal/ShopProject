package com.shopproject.base;

import com.shopproject.actiondriver.Action;
import com.shopproject.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public static Properties prop;
    // public static WebDriver driver;

    //declare ThreadLocal driver/
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeSuite(groups = {"Smoke", "Sanity", "Regression"})
    public void loadConfig() throws IOException {
        ExtentManager.setExtent();
        DOMConfigurator.configure("log4j.xml");
        try {
            prop = new Properties();
            System.out.println("Super constructor invoked");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/Configuration/Config.properties");
            prop.load(ip);
            System.out.println("driver: " + prop.getProperty("driver"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }

    public static Action action = new Action();


    public void launchApp(String browserName) {
       // String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver();
            //Set browser to ThreadLocalMap
            driver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver();
            driver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            //driver = new InternetExplorerDriver();
            driver.set(new InternetExplorerDriver());
        } else if (browserName.equalsIgnoreCase("Safari")) {
            WebDriverManager.safaridriver().setup();
            //driver = new SafariDriver();
            driver.set(new SafariDriver());
        }
        //Maximize the window
        getDriver().manage().window().maximize();
        //Navigate to url
        getDriver().get(prop.getProperty("url"));
        action.implicitWait(driver.get(), 10);
    }
    @AfterSuite
    public void afterSuite(){
        ExtentManager.endReport();
    }
}
