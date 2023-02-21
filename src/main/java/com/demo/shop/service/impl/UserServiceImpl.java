package com.demo.shop.service.impl;

import com.demo.shop.entity.User;
import com.demo.shop.mapper.UserMapper;
import com.demo.shop.service.IUserService;
import com.demo.shop.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 *  用户模块的业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void regdit(User user) {
        //在注册前需要确定用户名是否被注册过
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if (result != null){
            throw new UsernameDuplicatedException("用户名已存在！");
        }
        else {
            //密码加密处理：采用md5加密算法，以下列形式连续加载三次
            //形式为：盐值+password+盐值，其中盐值就是一个随机的字符串
            String oldPassword = user.getPassword();
            //随机生成一个盐值
            String salt = UUID.randomUUID().toString().toUpperCase();
            //调用定义的加密方法
            String md5Password = getMD5Password(oldPassword, salt);
            user.setPassword(md5Password);
            user.setSalt(salt);

            //插入用户补全数据：createdUser，createdTime，modifiedUser，modifiedTime，isDelete。
            Date date = new Date();
            user.setIsDelete(0);
            user.setCreatedUser(username);
            user.setCreatedTime(date);
            user.setModifiedUser(username);
            user.setModifiedTime(date);

            //判断在插入过程中是否出现异常
            Integer rows = userMapper.insert(user);
            if (rows != 1){
                throw new InsertException("注册过程发生错误！");
            }
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名判断用户是否存在
        User result = userMapper.findByUsername(username);
        if (result == null){
            throw new UserNotFoundException("该用户不存在！");
        }
        //在判断该用户的密码是否正确
        //此时注意数据库中的密码经过md5加密过，这时应当先获取当前用户数据库中的密码及盐值
        String RealtPassword = result.getPassword();
        String salt = result.getSalt();
        //然后将用户输入的密码结合该用户的盐值按照相同的md5加密规则进行加密
        String UnknownPassword = getMD5Password(password,salt);
        //再与数据库中的密码进行判断是否相同
        if (!RealtPassword.equals(UnknownPassword)){
            throw new PasswordNotMatchException("密码输入错误！请重新输入");
        }
        //判断is_delete字段是否为1，表示用户是否删除
        if(result.getIsDelete() == 1){
            throw new UserNotFoundException("该用户不存在");
        }
        //调用findByUsername方法查询用户数据并返回
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("该用户不存在！");
        }
        //将原始密码和数据库中密码比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码输入错误！");
        }
        //以上验证通过，将新密码加密存入数据库
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (rows == null) {
            throw new UpdateException("更新出现错误！");
        }
    }

    @Override
    public User getInfoByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("数据修改出现异常！");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        //在上传头像之前确定用户是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("该用户不存在！");
        }
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows != 1) {
            throw new UpdateException("数据修改出现异常！");
        }
    }

    //定义一个MD5算法的加密
    private String getMD5Password(String password,String salt){
        for (int i =0;i < 3; i++){
             password = DigestUtils.md5DigestAsHex((salt +password+ salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
