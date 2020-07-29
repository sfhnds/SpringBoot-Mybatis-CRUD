package com.kuang.service;

import com.kuang.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryUserList();
    User queryUserById(int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int id);
}
