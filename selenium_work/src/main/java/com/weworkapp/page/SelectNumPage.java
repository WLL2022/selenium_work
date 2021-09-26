package com.weworkapp.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SelectNumPage extends AppBasePage {

    public SelectNumPage(AppiumDriver driver) {
        super(driver);
    }

    public SearchPage toSearch(){
        driver.findElement(By.id("izy")).click();
        return new SearchPage(driver);
    }

    public TodoPage confirm(){
        click(By.id("hmu"));
        //driver.findElement(By.id("hmu")).click();
        return new TodoPage(driver);
    }

}
