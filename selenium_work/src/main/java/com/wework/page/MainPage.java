package com.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 首页PO
 */
public class MainPage extends BasePage {

    //使用cookies
    public MainPage() {
        super();
        //设置复用浏览器
        /*ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");*/
        /*driver = new ChromeDriver();
        //获取浏览器
        //setUpGetCookies(options,driver);
        //隐式等待，避免代码执行过快，页面未渲染时取不到元素，在设定时间内直到取到元素为止
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);*/
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?from=myhome");
        driver.manage().deleteAllCookies();

        //读取cookies，使用得到的cookies登陆
        getCookies(driver);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

    }

    //点击通讯录
    public ContactsPage toContacts() {
        click(By.cssSelector("#menu_contacts"));
        return new ContactsPage(driver);
    }

    //获取cookies
    static void setUpGetCookies(ChromeOptions options, RemoteWebDriver driver) {
        driver = new ChromeDriver(options);
        //对cookies遍历，将得到的cookie存放到文件中
        saveCookies(driver);
        System.out.println("cookies保存完成");
    }

    private void getCookies(RemoteWebDriver driver) {
        try {
            FileReader fileReader = new FileReader("cookies.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //StringTokenizer 分词器
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                String name = tokenizer.nextToken();
                String value = tokenizer.nextToken();
                String path = tokenizer.nextToken();
                String domain = tokenizer.nextToken();
                Date expiry = null;
                String expiryStr = tokenizer.nextToken();
                if (!expiryStr.equals("null")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    expiry = sdf.parse(expiryStr);
                }
                boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void saveCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println(cookies);
        try {
            //写入文件
            FileWriter fileWriter = new FileWriter("cookies.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie c : cookies) {
                bufferedWriter.write(c.getName() + ";" +
                        c.getValue() + ";" +
                        c.getPath() + ";" +
                        c.getDomain() + ";" +
                        c.getExpiry() + ";" +
                        c.isSecure());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
