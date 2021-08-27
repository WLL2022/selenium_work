package com.testcase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * 弹框处理
 */
public class AlertTest extends BaseTest{

    @Test
    public void alertTest(){
        try {
            driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");
            driver.switchTo().frame("iframewrapper");
            Actions action = new Actions(driver);
            action.dragAndDrop(driver.findElement(By.id("draggable")),driver.findElement(By.id("droggable"))).perform();

            Thread.sleep(5000);
            //弹框处理
            driver.switchTo().alert().accept();

            driver.switchTo().parentFrame();
            System.out.println(driver.findElement(By.id("submitBTN")).getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
