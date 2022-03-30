package com.yang.udfile.controller;
import com.yang.udfile.entity.User;
import com.yang.udfile.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-03-29 23:57:23
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login( User user, HttpSession session){
        User userDB = userService.queryUser(user);
        if (userDB != null) {
            session.setAttribute("user",userDB);
            return "redirect:/file/showAll";
        }else {
            return "redirect:/login";
        }
    }
}

