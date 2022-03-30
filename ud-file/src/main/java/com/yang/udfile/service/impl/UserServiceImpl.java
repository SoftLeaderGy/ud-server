package com.yang.udfile.service.impl;

import com.yang.udfile.dao.UserDao;
import com.yang.udfile.entity.User;
import com.yang.udfile.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-03-29 23:57:25
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public User queryUser(User user) {
        return userDao.queryUser(user);
    }
}
