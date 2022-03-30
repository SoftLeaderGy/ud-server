package com.yang.udfile.service;

import com.yang.udfile.entity.User;
import com.yang.udfile.entity.UserFile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-03-29 23:57:24
 */
public interface UserFileService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<UserFile> queryUserFile(String userId);


}
