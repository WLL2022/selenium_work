package com.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BasePage {

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public BasePage() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "android");
            caps.setCapability("deviceName", "雪球测试");
            caps.setCapability("appPackage", "com.xueqiu.android");
            caps.setCapability("appActivity", ".view.WelcomeActivityAlias");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("noReset", "true");
            driver = new AndroidDriver<>(new URL(" http://localhost:4723/wd/hub"), caps);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void clicks(By by, int i) {
        driver.findElements(by).get(i).click();
    }

    public void sendKeys(By by, String key) {
        driver.findElement(by).sendKeys(key);
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public boolean isDisplayed(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public void qiut() {
        try {
            Thread.sleep(5000);
            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
