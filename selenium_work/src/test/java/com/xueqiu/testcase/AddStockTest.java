package com.xueqiu.testcase;

import com.xueqiu.page.AddStockPage;
import com.xueqiu.page.MainPage;
import com.xueqiu.page.SearchPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AddStockTest {
    static AddStockPage addStockPage;

    @BeforeAll
    static void setUp() {
        addStockPage = new MainPage().toHangQing();
        //检查是否存在自选股票
        if (!addStockPage.checkStock()) {
            //删除
            addStockPage.delete();
        }
    }

    @DisplayName("添加自选股票")
    @ParameterizedTest
    @CsvSource({"alibaba, 阿里巴巴", "jd, 京东", "wy, 网易"})
    void testAddStock(String key, String result) {
        //搜索
        String name = addStockPage.toSearch().search(key).addStock().getFirstStockName();
        Assertions.assertEquals(result, name);
    }

    @AfterAll
    static void tearDane() {
        addStockPage.qiut();
    }
}
