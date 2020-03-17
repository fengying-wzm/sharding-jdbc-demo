package com.wuzhimin.shardingjdbcdemo.service;

import com.wuzhimin.shardingjdbcdemo.dao.UserMapper;
import com.wuzhimin.shardingjdbcdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(){
        User user = new User();
        user.setId(2);
        user.setName("wuzm");
        userMapper.insert(user);
        return userMapper.selectByPrimaryKey(2);
    }


}
