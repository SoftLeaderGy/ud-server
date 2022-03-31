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

    @Autowired(required = false)
    private UserFileDao userFileDao;
    @Override
    public List<UserFile> queryUserFile(String userId) {
        return userFileDao.queryUserFile(userId);
    }

    @Override
    public void saveFileInfo(UserFile userFile) {
        userFile.setDowncount(0);
        userFileDao.saveFileInfo(userFile);
    }

    @Override
    public UserFile findById(String id) {
        return userFileDao.findById(id);
    }

    @Override
    public void update(UserFile userFile) {
        userFileDao.update(userFile);
    }

    @Override
    public void delect(String id) {
        userFileDao.delect(id);
    }
}
