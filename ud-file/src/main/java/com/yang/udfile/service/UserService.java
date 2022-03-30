package com.yang.udfile.service;

import com.yang.udfile.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-03-29 23:57:24
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryUser(User user);


}
