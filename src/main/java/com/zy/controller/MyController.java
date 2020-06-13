package com.zy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }


}
