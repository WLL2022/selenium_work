package com.weworkapp_frame.page;

import com.wework.page.WebBasePage;
import com.weworkapp.page.AppBasePage;

//实现多平台可驱动
public class UIAutoFactory {

    public static BasePage create(String driverName){
        if(driverName.equals("web") || driverName.equals("selenium")){
            return new WebBasePage();
        }
        if(driverName.equals("app") || driverName.equals("appium")){
            return new AppBasePage();
        }
        if(driverName.equals("uiautomator")){
            //return ...
        }
        //...
        return null;
    }
}
