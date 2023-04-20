package com.nov.service;

import com.nov.spring.NovApplicationContext;

public class Test {
    public static void main(String[] args) {
        //新建Spring容器时，传一个配置类，根据配置去扫描
        NovApplicationContext applicationContext = new NovApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
    }
}
