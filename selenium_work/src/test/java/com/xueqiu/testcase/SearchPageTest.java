package com.xueqiu.testcase;

import com.xueqiu.page.MainPage;
import com.xueqiu.page.SearchPage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchPageTest {
    static MainPage mainPage;
    static SearchPage searchPage;

    @BeforeAll
    static void setUp() {
        searchPage = new MainPage().toSearch();
    }

    @Order(1)
    @DisplayName("搜索")
    @ParameterizedTest(name = "{index} ==> first=''{0}'', second={1}")
    @CsvSource({
            "alibaba, 阿里巴巴",
            "jd, 京东"
    })
    void testSearch(String key, String result) {
        //todo:assert
        assertEquals(result, searchPage.search(key).getResultList().get(0));
    }

    @Test
    @Order(2)
    @DisplayName("获取搜索结果股价")
    void testGetPrice() {
        Double price = searchPage.search("alibaba").getPrice();
        assertTrue(price < 200);
    }

    @AfterAll
    static void tearDawn() {
        searchPage.qiut();
    }

}
