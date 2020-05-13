package com.blog.blogProduct.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        System.out.println("index");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }


}
