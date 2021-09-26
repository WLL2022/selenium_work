package com.weworkapp.page;

import org.openqa.selenium.By;

public class MainPage extends AppBasePage {


    public TodoPage toTodo(){
        driver.findElement(By.id("iww")).click();
        return new TodoPage(driver);
    }

    public SearchPage toSearch(){
        driver.findElement(By.id("j04")).click();
        return new SearchPage(driver);
    }
}
