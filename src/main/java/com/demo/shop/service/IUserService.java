package com.demo.shop.service;

import com.demo.shop.entity.User;

/**
 *  用户模块的业务层接口
 */
public interface IUserService {
    //用户注册功能
    void regdit(User user);
    //用户登录功能，返回匹配的用户数据，没有则返回null
    User login(String username,String password);
    //修改密码功能
    void changePassword(Integer uid,String username,String oldPassword,String newPassword);
    //根据uid获取用户信息
    User getInfoByUid(Integer uid);
    //更新用户信息
    void changeInfo(Integer uid,String username,User user);
    //上传头像
    void changeAvatar(Integer uid,String avatar,String username);
}
