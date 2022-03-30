package com.yang.udfile.dao;

import com.yang.udfile.entity.UserFile;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/17:37
 */
public interface UserFileDao {
    List<UserFile> queryUserFile(String userId);
}
