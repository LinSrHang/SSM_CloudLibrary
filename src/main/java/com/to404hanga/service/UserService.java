package com.to404hanga.service;

import com.to404hanga.domain.User;

/**
 * 用户接口
 */

public interface UserService {
    //通过User的用户账号和用户密码查询用户信息
    User login(User user);
    User checkEmail(User user);
    Integer addUser(String user_name,String user_password,String user_email,String user_role);
}
