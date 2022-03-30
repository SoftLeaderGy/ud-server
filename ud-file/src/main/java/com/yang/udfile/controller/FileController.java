package com.yang.udfile.controller;

import com.yang.udfile.entity.User;
import com.yang.udfile.entity.UserFile;
import com.yang.udfile.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/00:07
 */
@RequestMapping("/file")
@Controller
public class FileController {


    @Autowired
    private UserFileService userFileService;
    @RequestMapping("/showAll")
    public String showAll(HttpSession session, Model model){

        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        List<UserFile> userFiles = userFileService.queryUserFile(user.getId());
        model.addAttribute("userFiles",userFiles);
        return "showAll";
    }
}
