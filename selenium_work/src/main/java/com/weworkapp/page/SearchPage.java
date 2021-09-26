package com.weworkapp.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends AppBasePage {

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public SelectNumPage search(String key) {
        //输入搜索内容
        send(By.id("hj5"),key);
        //选中第一个搜索结果
        click(By.id("epp"));
        return new SelectNumPage(driver);
    }

    public SearchPage searchAll(String key) {
        //输入搜索内容
        send(By.id("hj5"),key);
        return this;
    }

    public List<String> getResult() {
        //查看第一个搜索结果
        return (List<String>) driver.findElements(By.id("epp")).stream().map(x->x.toString()).collect(Collectors.toList());
        /*List<String> resList = new ArrayList<>();
        driver.findElements(By.id("epp")).forEach(element -> {
            resList.add(element.toString());
        });
        return resList;*/
    }
}
