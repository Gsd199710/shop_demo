package com.demo.shop.controller;

import com.demo.shop.controller.ex.*;
import com.demo.shop.service.ex.*;
import com.demo.shop.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 *  控制层处理异常的基类
 */
public class BaseController {
    //操作成功的状态码
    public static final int OK = 101;
    @ExceptionHandler({ServiceException.class,FileUploadException.class})   //用于统一处理抛出的异常
    //请求处理异常的方法，其返回值需要传递给前端，则返回值类型需要用JsonResult<Void>
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已存在！");
        }else if (e instanceof UserNotFoundException) {
            result.setState(4004);
            result.setMessage("用户名不存在！");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(4005);
            result.setMessage("账号密码输入错误！");
        }else if (e instanceof AddressCountLimitedEXception) {
            result.setState(4006);
            result.setMessage("用户地址数量超出上限！");
        }else if (e instanceof AccessDeniedException) {
            result.setState(4007);
            result.setMessage("非法访问用户数据！");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4008);
            result.setMessage("用户地址不存在！");
        }else if (e instanceof DeleteException) {
            result.setState(4009);
            result.setMessage("用户地址不存在！");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("用户名注册出现未知错误！");
        }else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("用户数据更新出现未知错误！");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(5002);
            result.setMessage("该商品不存在！");
        }else if (e instanceof FileUploadIOException) {
            result.setState(6001);
            result.setMessage("文件上传过程出现错误！");
        }else if (e instanceof FileEmptyException) {
            result.setState(6002);
            result.setMessage("上传文件不能为空错误！");
        }else if (e instanceof FileStateEXception) {
            result.setState(6003);
            result.setMessage("上传文件未关闭错误！");
        }else if (e instanceof FileSizeException) {
            result.setState(6004);
            result.setMessage("上传文件超出错误！");
        }
        return result;
    }

    /**
     *  获取当前session对象中的uid
     * @param session session对象
     * @return uid
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     *  获取当前session对象中的username
     * @param session
     * @return username
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
