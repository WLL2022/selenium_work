package com.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    private By name = By.id("name");

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public SearchPage search(String key) {
        //todo:模拟器比较慢时，操作无效，可循环检查
        do {
            sendKeys(By.id("search_input_text"), key);
        } while (driver.findElements(name).size() <= 0);

        return this;
    }

    public List<String> getResultList() {
        List<String> nameList = new ArrayList<>();
        /*for (Object e: driver.findElements(By.id("name"))){
            nameList.add(((WebElement)e).getText());
        }*/
        //todo:优化为lambda表达式
        driver.findElements(name).forEach(element -> {
            nameList.add(element.getText());
        });
        return nameList;
    }

    ;

    public Double getPrice() {
        click(name);
        return Double.parseDouble(getText(By.id("current_price")));
    }

    public AddStockPage addStock() {
        //点击第一条
        click(name);
        //第一条加入自选
        click(By.id("follow_btn"));
        //点击取消
        click(By.id("action_close"));
        return new AddStockPage(driver);
    }

}
