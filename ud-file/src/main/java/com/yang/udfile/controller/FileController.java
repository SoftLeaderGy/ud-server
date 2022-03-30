package com.yang.udfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/00:07
 */
@RequestMapping("/file")
@Controller
public class FileController {

    @RequestMapping("/showAll")
    public String showAll(){
        return "showAll";
    }
}
