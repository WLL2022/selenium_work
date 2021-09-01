package com.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.nio.cs.UnicodeEncoder;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 通讯录PO
 */
public class ContactsPage extends BasePage{
    By addMumber = By.linkText("添加成员");

    public ContactsPage(RemoteWebDriver driver) {
        super(driver);
    }

    //添加成员
    public ContactsPage addMumber(String username, String acctid, String phone) {
        //显示等待,先查看元素是否出现，然后点击,这种方式还是存在找到元素但点击无效的情况，建议使用下面的方法
        /*new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(addMumber));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(addMumber));*/

        //循环检查元素,直到元素出现，使用username作为循环条件也可以，只要找不到username就一直循环
        while(driver.findElements(addMumber).size() > 0){
            click(addMumber);
        }
        sendKeys(By.name("username"),username);
        sendKeys(By.id("memberAdd_acctid"),acctid);
        sendKeys(By.id("memberAdd_phone"),phone);
        click(By.cssSelector(".js_btn_save"));
        return this;
    }

    //搜索成员
    public ContactsPage search(String s) {
        sendKeys(By.id("memberSearchInput"),s);
        return this;
    }

    //获取用户名
    public String getUsername(){
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }

    //删除成员
    public ContactsPage delete() {
        click(By.linkText("删除"));
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
        return this;
    }
    //导入文件
    public ContactsPage uploadFile(URL path) {
        try {
            //解决中文乱码问题
            String path_utf = URLDecoder.decode(path.getFile(),"UTF-8");
            click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
            click(By.linkText("文件导入"));
            upload(By.id("js_upload_file_input"), path_utf);
            click(By.linkText("确认导入"));
            click(By.id("reloadContact"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    //添加部门
    public void addDept(String name, String name_en) {
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),name);
        sendKeys(By.name("name_en"),name_en);
        click(By.linkText("选择所属部门"));
        click(By.cssSelector(".jstree-2 #\\31 688850902890356_anchor"));
        click(By.linkText("确定"));
    }

    //添加标签
    public void addTag(String ceo) {
        click(By.linkText("标签"));
        click(By.cssSelector(".member_colLeft_top_addBtn"));
        sendKeys(By.name("name"),ceo);
        click(By.linkText("确定"));
    }
    //删除部门
    public void deleteDept(String department) {
        click(By.xpath("//a[text()=\""+ department + "\"]"));
        click(By.xpath("//a[text()=\""+ department + "\"]/span"));
        click(By.xpath("(//a[contains(text(),'删除')])[3]"));
        click(By.linkText("确定"));
    }
    //获取成功提示
    public String getTips(){
        String tips = getText(By.id("js_tips"));
        return tips;
    }
}
