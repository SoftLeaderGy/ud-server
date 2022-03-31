package com.yang.udfile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/16:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFile {
    private String id;
    private String oldFileName;
    private String newFileName;
    private String ext;
    private String path;
    private String size;
    private String type;
    private String isImg;
    private Integer downcount;
    private String uploadTime;
    private String userId;
}
