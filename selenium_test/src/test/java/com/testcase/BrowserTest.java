package com.testcase;

import org.junit.jupiter.api.Test;

/**
 * 多浏览器
 */
public class BrowserTest extends BaseTest{
    @Test
    public void browserTest(){
        driver.get("https://ceshiren.com/");
    }
}
