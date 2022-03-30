package com.yang.udfile.service.impl;

import com.yang.udfile.dao.UserFileDao;
import com.yang.udfile.entity.UserFile;
import com.yang.udfile.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/30/17:48
 */
@Service("UserFileServiceImpl")
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    private UserFileDao userFileDao;
    @Override
    public List<UserFile> queryUserFile(String userId) {
        return userFileDao.queryUserFile(userId);
    }
}
