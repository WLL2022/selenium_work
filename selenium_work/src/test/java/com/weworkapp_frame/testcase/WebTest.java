package com.weworkapp_frame.testcase;

import com.weworkapp_frame.bean.UIAuto;
import com.weworkapp_frame.page.BasePage;
import com.weworkapp_frame.page.UIAutoFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WebTest {
    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        //todo: 加载通用配置
    }

    @BeforeEach
    void beforeEach(){
        //todo: 每个用例相关
    }

    @ParameterizedTest(name = "{index}-{1}")
    @MethodSource
    void classic(UIAuto uiAuto, String path){
        String result = basePage.run(uiAuto);
        assertEquals("passed", result);
    }

    static List<Arguments> classic(){
        basePage = UIAutoFactory.create("web");
        basePage.loadPages("src/main/resources/test_frame");
        List<Arguments> all= new ArrayList<Arguments>();
        Arrays.asList(
//                "/test_frame/uiauto_web.yaml",
//                "/test_frame/uiauto_web_1.yaml",
                "/test_frame/weixin_add_member.yaml"
        ).stream().forEach(path->{
            UIAuto uiAuto= basePage.load(path);
            uiAuto.description=path;
            all.add(arguments(uiAuto, path));
        });
        return all;
    }
}
