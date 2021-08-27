package com.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 多浏览器兼容验证基类
 */
public class BaseTest {
    public static WebDriver driver;

    @BeforeAll
    public static void init(){
        String browserName = System.getenv("browser");
        if("chrome".equals(browserName)){
            System.setProperty("webdriver.chrome.driver","/Users/wll/softwares/selenium/chromedriver");
            driver = new ChromeDriver();
        }else if("firefox".equals(browserName)){
            driver = new FirefoxDriver();
        }else if("safari".equals(browserName)){
            driver = new SafariDriver();
        }
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
