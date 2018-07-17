package com.tingyun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 返回 login 页面
     * @return
     */
    @RequestMapping("/page")
    public String page(){
        return "login";
    }





}
