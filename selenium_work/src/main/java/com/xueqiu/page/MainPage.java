package com.xueqiu.page;

import org.openqa.selenium.By;

public class MainPage extends BasePage {

    public SearchPage toSearch() {
        click(By.id("com.xueqiu.android:id/tv_search"));
        return new SearchPage(driver);
    }

    public AddStockPage toHangQing() {
        clicks(By.id("com.xueqiu.android:id/tab_name"), 1);
        return new AddStockPage(driver);
    }
}
