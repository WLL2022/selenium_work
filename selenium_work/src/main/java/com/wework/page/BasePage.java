package com.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.wework.page.MainPage.setUpGetCookies;

/**
 * PO基类
 */
public class BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public BasePage(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        //需先获取浏览器cookie
        //setUpGetCookies(options,driver);
        driver = new ChromeDriver();

        //隐式等待，避免代码执行过快，页面未渲染时取不到元素，在设定时间内直到取到元素为止
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }

    //封装click
    public void click(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    //封装sendkeys
    public void sendKeys(By by,String keys){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);
    }
    //封装上传
    public void upload(By by, String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }
    //封装gettext
    public String getText(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    //关闭浏览器
    public void qiut(){
        driver.quit();
    }


}
