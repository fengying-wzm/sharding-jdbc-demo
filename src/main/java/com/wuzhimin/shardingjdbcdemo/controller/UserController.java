package com.wuzhimin.shardingjdbcdemo.controller;

import com.wuzhimin.shardingjdbcdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhimin
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public Object getName(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg",userService.getUser().getName());
        return map;
    }

}
