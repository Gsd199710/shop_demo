package com.demo.shop.service;

import com.demo.shop.entity.User;
import com.demo.shop.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Locale;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;
    @Test
    public void regdit(){
        try {
            User user = new User();
            user.setUsername("ben04");
            user.setPassword("123");
            iUserService.regdit(user);
            System.out.println("创建成功！");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void login(){
        User user = iUserService.login("ben03", "888888");
        System.out.println(user);
    }
    @Test
    public void changePassword(){
        iUserService.changePassword(10,"admin","123","666");
    }
    @Test
    public void getInfoByUid(){
        System.out.println(iUserService.getInfoByUid(10));
    }
    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("1777777773");
        user.setEmail("1777777773@qq.com");
        user.setGender(0);
        iUserService.changeInfo(10,"Ben",user);
    }
    @Test
    public void changeAvatar(){
        iUserService.changeAvatar(10,"/upload/avatar001.jpg","admin01");
    }
}
