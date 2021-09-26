package com.weworkapp.page;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class TodoPage extends AppBasePage {

    SelectNumPage selectNumPage = new SelectNumPage(driver);

    public TodoPage(AppiumDriver driver) {
        super(driver);
    }

    public TodoPage addTodo(String message, String key) {
        //点击添加按钮
        click(By.id("izt"));
        //输入待办内容
        send(By.id("bra"),message);
        //选择参与人
        click(By.xpath("//*[@text='请选择']"));
        //--搜索成员并确认
        selectNumPage.toSearch().search(key).confirm();
        //选择日期
        click(By.xpath("//*[@text='无提醒']"));
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.view.ViewGroup/android.widget.GridView/android.view.View[13]"));
        click(By.id("cm2"));
        //点击保存
        click(By.id("iyd"));
        return this;
    }

    public String getTodoName() {
        return driver.findElement(By.id("iwj")).getText();
    }
}
