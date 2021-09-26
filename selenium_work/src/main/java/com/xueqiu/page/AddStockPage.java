package com.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddStockPage extends BasePage {

    public AddStockPage(AppiumDriver driver) {
        super(driver);
    }

    public boolean checkStock() {
        try {
            isDisplayed(By.id("fl_empty_container"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public AddStockPage delete() {
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("com.xueqiu.android:id/edit_group")));
        //进入管理自选股票页
        //todo:循环点击直到下一个控件出现
        do {
            click(By.id("edit_group"));
        } while (!isDisplayed(By.id("check_all")));

        //全选
        click(By.id("check_all"));
        //取消关注
        click(By.id("cancel_follow"));
        //点击确定
        click(By.id("tv_right"));
        //点击完成
        click(By.id("action_close"));
        return this;
    }

    public SearchPage toSearch() {
        click(By.id("action_search"));
        return new SearchPage(driver);
    }


    public String getFirstStockName() {
        //可使用findElement 替换，findElement 默认取第一个，比findElements 快
        //WebElement element = driver.findElements(By.id("portfolio_stockName")).get(0);
        return getText(By.id("portfolio_stockName"));

    }
}
