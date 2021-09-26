package com.wework.page;

import com.weworkapp_frame.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.wework.page.MainPage.setUpGetCookies;

/**
 * PO基类
 */
public class WebBasePage extends BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        //需先获取浏览器cookie
        //setUpGetCookies(options,driver);
        driver = new ChromeDriver();

        //隐式等待，避免代码执行过快，页面未渲染时取不到元素，在设定时间内直到取到元素为止
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    //封装click
    public void click(By by) {
        if (by != null) {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).click();
        } else {
            System.out.println("by == null");
        }
    }

    //封装sendkeys
    public void sendKeys(By by, String keys) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keys);
    }

    //封装上传
    public void upload(By by, String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

    //封装gettext
    public String getText(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    //关闭浏览器
    public void qiut() {
        driver.quit();
    }

    @Override
    public void click(HashMap<String, Object> m) {
        super.click(m);
        String key = (String) m.keySet().toArray()[0];
        String value = (String) m.values().toArray()[0];
        By by = null;
        if (key.toLowerCase().equals("id".toLowerCase())) {
            by = By.id(value);
        }
        if (key.toLowerCase().equals("xpath".toLowerCase())) {
            by = By.xpath(value);
        }
        if (key.toLowerCase().equals("linkText".toLowerCase())) {
            by = By.linkText(value);
        }
        click(by);
    }

    @Override
    public void action(HashMap<String, Object> m) {
        super.action(m);
        if (m.containsKey("action")) {
            String action = m.get("action").toString().toLowerCase();
            if (action.equals("get")) {
                String url = m.get("url").toString();
                driver.get(url);
                driver.manage().deleteAllCookies();
                //读取cookies，使用得到的cookies登陆
                getCookies(driver);
                driver.get(url);
            } else {
                System.out.println("error driver.get");
            }
        }
    }

    @Override
    public String getResult(HashMap<String, Object> m) {
        super.getResult(m);
        HashMap<String, Object> result = (HashMap<String, Object>) m.get("expect");

        String actualResult = driver.findElement(getLocator(result)).getText();
        String expectedResult = (String) result.get("result");

        return actualResult.equals(expectedResult) ? "passed" : "failed";
    }

    @Override
    public void sendKeys(HashMap<String, Object> map,HashMap<String, Object> params) {
        super.sendKeys(map, params);
        String keys = null;
        if(null == params) {
            keys = (String)map.get("sendKeys");
        } else {
            //如果参数不为空，从参数中获取实际要输入的值
            keys = (String)params.get(map.get("sendKeys"));
        }
        sendKeys(getLocator(map), keys);
    }

    public void getCookies(RemoteWebDriver driver) {
        try {
            FileReader fileReader = new FileReader("cookies.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //StringTokenizer 分词器
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                String name = tokenizer.nextToken();
                String token = tokenizer.nextToken();
                String value;
                String path;
                if (token.equals("/")) {
                    value = "";
                    path = token;
                } else {
                    value = token;
                    path = tokenizer.nextToken();
                }
//                System.out.println("value--------" + value);
//                System.out.println("path--------" + token);

                String domain = tokenizer.nextToken();
                Date expiry = null;
                String expiryStr = tokenizer.nextToken();
                //System.out.println("expiryStr--------" + expiryStr);
                if (!expiryStr.equals("null")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    expiry = sdf.parse(expiryStr);
                    //System.out.println("expiry--------" + expiry);
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

    public static void saveCookies(WebDriver driver) {
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
