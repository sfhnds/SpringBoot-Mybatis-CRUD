package com.kuang.controller;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired

    private UserMapper userMapper;
    @RequestMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList= userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(userList);
        }
        return userList;
    }
    @RequestMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(2,"xiaoli","asdf2"));
        return "User ADD";
    }
    @RequestMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(2,"wangwu","sseadf"));
        return "User Update Successful";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(2);
        return "Delete Successful";
    }
}
