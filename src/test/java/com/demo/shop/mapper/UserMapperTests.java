package com.demo.shop.mapper;

import com.demo.shop.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest //表示当前类是测试类，不会随项目一块打包
@RunWith(SpringRunner.class)//表示启动这个单元测试类(没有则无法运行)，必须传递一个Spring Runner的实例类型
public class UserMapperTests {
    @Autowired
    //报错的原因是：接口本来不能创建bean，这里有Mybatis的动态代理实现创建bean的过程
    private UserMapper userMapper;
//    @Test
//    public void insert(){
//        User user = new User();
//        user.setUserName("tom");
//        user.setPassWord("123456");
//        Integer row = userMapper.insert(user);
//        System.out.println(row);
//    }
    @Test
    public void findByUserName(){
        User user = userMapper.findByUsername("tom");
        System.out.println(user);
    }
    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(9,"123","admin",new Date());
    }
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(9));
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(10);
        user.setPhone("123456677");
        user.setEmail("123456677@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(10,"/upload/avatar.jpg","admin",new Date());

    }
}
