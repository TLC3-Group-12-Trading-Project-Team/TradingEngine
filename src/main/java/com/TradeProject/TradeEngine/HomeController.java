package com.TradeProject.TradeEngine;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "Hello world!!";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world!!";
    }
}