package com.weworkapp_frame.testcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weworkapp_frame.bean.UIAuto;
import com.weworkapp_frame.page.BasePage;
import org.junit.jupiter.api.*;

class BasePageTest {
    static BasePage basePage;

    @BeforeAll
    static void setUp() {
        basePage = new BasePage();
    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void run() {
        UIAuto uiAuto = basePage.load("/test_frame/uiauto.yaml");
        basePage.run(uiAuto);
    }

    @Test
    void runPOM(){
        basePage.loadPages("src/main/resources/test_frame");
        UIAuto uiauto=basePage.load("/test_frame/weixin_add_member.yaml");
        basePage.run(uiauto);
    }

    @Test
    void load() throws JsonProcessingException {
        UIAuto uiAuto = basePage.load("/test_frame/uiauto.yaml");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(uiAuto));
    }
}