package com.weworkapp_frame.testcase;

import com.weworkapp_frame.bean.UIAuto;
import com.weworkapp_frame.page.BasePage;
import com.weworkapp_frame.page.UIAutoFactory;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class UIAutoFactoryTest {

    static BasePage device;

    @BeforeAll
    static void setUp(){
        device = UIAutoFactory.create("web");
    }

    @Test
    void createTest() {
        UIAuto uiAuto = device.load("/uiauto_web.yaml");
        device.run(uiAuto);
    }


}