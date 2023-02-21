package com.demo.shop.controller;

import com.demo.shop.controller.ex.FileEmptyException;
import com.demo.shop.controller.ex.FileSizeException;
import com.demo.shop.controller.ex.FileTypeException;
import com.demo.shop.controller.ex.FileUploadIOException;
import com.demo.shop.entity.User;
import com.demo.shop.service.IUserService;
import com.demo.shop.service.ex.InsertException;
import com.demo.shop.service.ex.UsernameDuplicatedException;
import com.demo.shop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController //效果等同与 @Controller+@ResponseBody
//@Controller
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService iUserService;
    @RequestMapping("regdit")
//    @ResponseBody   //以Json格式进行数据的响应
    public JsonResult<Void> regdit(User user) {
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<Void>();
        iUserService.regdit(user);
        return new JsonResult<Void>(OK);
    }

    //登录功能
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password,
                                  HttpSession session){
        User userdata = iUserService.login(username, password);
        //在session对象中绑定uid数据，该数据可以全局访问
        session.setAttribute("uid",userdata.getUid());
        session.setAttribute("username",userdata.getUsername());
        //获取session对象中的数据
//        System.out.println(getuidFromSession(session));
//        System.out.println(getUsernameFromSession(session));
        return new JsonResult<>(OK,userdata);
    }
    //修改密码功能
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        iUserService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
    //查询用户存在
    @RequestMapping("get_by_uid")
    public JsonResult<User> getInfoByUid(HttpSession session){
        User data = iUserService.getInfoByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    //修改用户信息
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        iUserService.changeInfo(getuidFromSession(session),getUsernameFromSession(session),user);
        return new JsonResult<>(OK);
    }
    //MultipartFile由SpringMVC提供的接口，可以获取任意文件类型的数据
    // @RequestMapping可以将请求的参数注入到请求处理方法的某个参数上
    /**
     * 设置头像上传文件的最大值
     */
   public static final int AVATAR_MAX_SIZE = 10 *1024 *1024;
    /**
     *  设置文件上传的格式
      */
   public static final List<String> AVATAR_TYPES = new ArrayList<>();
   static {
       AVATAR_TYPES.add("image/jpeg");
       AVATAR_TYPES.add("image/png");
       AVATAR_TYPES.add("image/bmp");
       AVATAR_TYPES.add("image/gif");
   }
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
                                           HttpSession session){
        if (file.isEmpty()) {
            throw new FileEmptyException("文件不能为空！");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("上传文件超过了指定的大小！");
        }
        String contentType = file.getContentType();
        if (!AVATAR_TYPES.contains(contentType) ) {
            throw new FileTypeException("文件格式错误！");
        }
        //上传文件，/upload/*.jpg
        String context = session.getServletContext().getRealPath("upload");
        System.out.println(context);
        File dir = new File(context);
        if (!dir.exists()) {
            dir.mkdirs();//判断文件目录是否存在，没有则创建
        }
        //获取当前文件名，并根据UUID重新生成一个新的字符串作为文件名防止多次上传有重复名的文件被覆盖掉
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String suffix = "";
        int index = originalFilename.lastIndexOf(".");
        if (index >0) {
            suffix = originalFilename.substring(index);
        }
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest = new File(dir,filename);
        try {
            file.transferTo(dest);//将file文件中的数据写入到dest文件中
        } catch (IOException e) {
            throw new FileUploadIOException("文件上传过程出现异常！");
        }catch (IllegalStateException e) {
            throw new FileSizeException("文件格式不匹配，上传失败！");
        }
        String avatar = "//upload/"+filename;
        iUserService.changeAvatar(getuidFromSession(session),getUsernameFromSession(session),avatar);
        return new JsonResult<>(OK,avatar);
    }
}
