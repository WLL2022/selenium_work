package com.weworkapp.page;

import com.weworkapp_frame.page.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppBasePage extends BasePage {
    public AppiumDriver driver;
    public WebDriverWait wait;


    public AppBasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public AppBasePage() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "android");
            caps.setCapability("deviceName", "企业微信APP测试");
            caps.setCapability("appPackage", "com.tencent.wework");
            caps.setCapability("appActivity", ".launch.LaunchSplashActivity");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("noReset", "true");
            caps.setCapability("skipDeviceInitialization","true");
            driver = new AndroidDriver<>(new URL(" http://localhost:4723/wd/hub"), caps);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public void send(By by,String key){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(key);
    }

    public void click(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void qiut(){
        driver.quit();
    }

}
