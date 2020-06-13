package com.zy.utils;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.context.annotation.Bean;

public class ShiroConfigUtil {

    @Bean
    public static ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
