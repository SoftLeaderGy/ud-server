package com.yang.udfile.controller;

import ch.qos.logback.core.util.FileUtil;
import com.yang.udfile.entity.User;
import com.yang.udfile.entity.UserFile;
import com.yang.udfile.service.UserFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/00:07
 */
@RequestMapping("/file")
@Controller
@Slf4j
public class FileController {


    @Autowired
    private UserFileService userFileService;

    @Value("${files.realPath}")
    private String realPath;
    @Value("${isPro}")
    private boolean isPro;
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file,HttpSession session) throws IOException {
    // 获取文件信息
        // 获取文件名称
        String oldFileName = file.getOriginalFilename();

        if(StringUtils.isEmpty(oldFileName)){
            return "redirect:/file/showAll";
        }
        // 生成新文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "-"+UUID.randomUUID().toString().replace("-","");
        // 获取文件后缀名
        String extension = "."+ FilenameUtils.getExtension(oldFileName);
        // 获取文件大小
        long size = file.getSize();
        // 获取文件类型
        String contentType = file.getContentType();

    // 动态生成文件目录，将上上传的文件存储在类路径下的files文件夹下
        // 获取类路径下下的绝对路径(获取到的是resource当下的路径)
        String path = ResourceUtils.getURL("classpath:").getPath();
//        log.info("path========>"+ path);
        // 拼接路径之files文件夹下
        String realPath = path+ "/static/files/";
        // 配置线上环境文件的存储路径
        if(isPro){
            realPath = this.realPath + "files/";
        }
        // 每天在files文件夹下创建一个以yyyy-MM-dd形式的文件夹名，每天上传的文件保存至此
        // 拼接每天创建文件夹的路径
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dataDirPath = realPath + format;
        // 创建目录下的文件
        File dataDir = new File(dataDirPath);
        // 判断每天的存储的路径是否在，如果不在创建文件夹
        if(!dataDir.exists()){
            dataDir.mkdirs();
        }
    // 处理文件上传
        file.transferTo(new File(dataDir,newFileName+ extension));
    // 将文件信息存入数据库中
        UserFile userFile = new UserFile();
        userFile.setId(UUID.randomUUID().toString().replace("-",""));
        userFile.setOldFileName(oldFileName);
        userFile.setNewFileName(newFileName+ extension);
        userFile.setExt(extension);
        userFile.setPath("/files/"+ format);
        userFile.setSize(size + "");
        userFile.setType(contentType);
        userFile.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        User user = (User) session.getAttribute("user");
        userFile.setUserId(user.getId());
        // file.getContentType()获取的文件类型如果是以image开头的 那就是图片类型
        String isImg = file.getContentType().startsWith("image") ? "是" : "否";
        userFile.setIsImg(isImg);
        userFileService.saveFileInfo(userFile);
        return "redirect:/file/showAll";

    }
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

    @GetMapping("/download")
    public void downloadFile(String openStyle,String id, HttpServletResponse response) throws IOException {
        openStyle = StringUtils.isEmpty(openStyle)? "accachment":openStyle;
        // 获取文件信息
        UserFile userFile = userFileService.findById(id);
        if(openStyle.equals("accachment")){
            // 更新下载次数
            userFile.setDowncount(userFile.getDowncount()+1);
            userFileService.update(userFile);
        }
        // 根据文件信息中的文件名字和文件路径获取文件的输入流
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();
        if(isPro){
           realPath = "/usr/local/nginx/html/ud-server"+ userFile.getPath();
        }
        log.info("realPath============>"+realPath);
        // 获取文件流
        FileInputStream is = new FileInputStream(new File(realPath,userFile.getNewFileName()));
        // 附件下载
        response.setHeader("content-disposition",openStyle + ";fileName="+ URLEncoder.encode(userFile.getOldFileName(),"UTF-8"));

        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @GetMapping("/delect")
    public String delect(String id) throws FileNotFoundException {
        // 根据id获取文件信息
        UserFile userFile = userFileService.findById(id);
        // 删除文件
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();
        File file = new File(realPath, userFile.getNewFileName());
        if(file.exists()){
            file.delete();
        }
        // 删除数据库
        userFileService.delect(id);
        return "redirect:/file/showAll";
    }
}
