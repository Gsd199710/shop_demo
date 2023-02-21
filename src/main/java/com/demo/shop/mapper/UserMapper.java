package com.demo.shop.mapper;

import com.demo.shop.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 *  用户模块的持久层接口
 */
public interface UserMapper {
    /**
     *  插入用户的数据
     *  返回数据库所影响的行数，根据返回值判断用户操作是否成功
     */
    Integer insert(User user);
    /**
     *  根据用户名查询用户数据
     */
    User findByUsername(String username);

    /**
     *  根据用户的uid修改密码
     * @param uid 用户id
     * @return  受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     *  根据用户id查询用户信息
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     *  更新用户的数据
     * @param user 一个含有uid，username，性别的user对象
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     *  根据用户uid上传用户头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    //@Parm("SQL映射文件中#{}占位符的变量")可以解决SQL语句的占位符与映射的接口方法参数名
    // 不一致的问题，将某个参数强行注入到某个占位符变量中
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
