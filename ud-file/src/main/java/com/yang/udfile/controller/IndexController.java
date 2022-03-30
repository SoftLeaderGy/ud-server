package com.yang.udfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/00:10
 */
@Controller
public class IndexController {

    @GetMapping("login")
    public String toLogin(){
        return "login";
    }
}
