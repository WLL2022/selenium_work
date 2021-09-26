package com.weworkapp.testcase;

import com.weworkapp.page.MainPage;
import com.weworkapp.page.SearchPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


public class SearchTest {
    private static MainPage mainPage;
    private static SearchPage searchPage;

    @BeforeAll
    void setUp() {
        searchPage = new MainPage().toSearch();
    }

    @ParameterizedTest
    @CsvSource({
            "zhang, 张三"
    })
    void searchTest(String key,String res) {
        assertTrue(searchPage.searchAll(key).getResult().contains(res));

    }

    @AfterAll
    void tearDown() {
        searchPage.qiut();
    }
}
