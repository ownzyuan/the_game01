package com.zy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AllMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("lol");
        registry.addViewController("/lol").setViewName("lol");
        registry.addViewController("/lol.html").setViewName("lol");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT","PATCH")
                .maxAge(3600);
    }

}
