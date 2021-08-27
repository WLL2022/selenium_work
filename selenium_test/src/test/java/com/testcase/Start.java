package com.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Start {
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeAll
    public static void beforeStart(){
        //System.setProperty("webdriver.chrome.driver","/Users/wll/softwares/selenium/chromedriver");
        driver = new ChromeDriver();
        //隐式等待
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void startSelenium(){
        driver.get("https://ceshiren.com/");
        try {
            //强制等待
            Thread.sleep(2000);
            //driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
            //driver.findElement(By.xpath("//*[@id='navigation-bar']//li[2]")).click();
            Actions action = new Actions(driver);
            action.click(driver.findElement(By.xpath("//span[contains(text(),'登录')]")));
            action.perform();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void xishidengdai(){
        driver.get("https://ceshiren.com/");

        //driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        /*WebElement loginEle = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
            }
        });

        loginEle.click();*/

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'登录')]")));
        element.click();
    }
    

    @AfterAll
    public static void afterAction(){
        driver.quit();
    }

}

