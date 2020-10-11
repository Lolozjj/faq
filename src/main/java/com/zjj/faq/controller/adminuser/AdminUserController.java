package com.zjj.faq.controller.adminuser;

import com.github.pagehelper.PageInfo;
import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.controller.adminuser.request.AdminUserAddRequest;
import com.zjj.faq.controller.adminuser.request.AdminUserModificationRequest;
import com.zjj.faq.service.outer.login.AdminUserService;
import com.zjj.faq.service.outer.login.response.AdminUserResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 阿呆的小鸡仔
 */
@RestController
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    @GetMapping("/adminUsers")
    public Msg getAllAdminUsers(int curPage,int pageSize){
        PageInfo<AdminUserResponse> allAdminUsers = adminUserService.getAllAdminUsers(curPage,pageSize);
        return Msg.success().add("adminUser",allAdminUsers);
    }

    @PostMapping("/adminUser")
    public Msg addAdminUser(@RequestBody AdminUserAddRequest adminUserRequest){
        if(adminUserService.addAdminUser(adminUserRequest)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @PutMapping("/adminUser")
    public Msg modificationAdminUser(@RequestBody AdminUserModificationRequest adminUserModificationRequest){
        if(adminUserService.modificationAdminUser(adminUserModificationRequest)){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @DeleteMapping("/adminUser/{account}")
    public Msg deleteAdminUser(@PathVariable("account") @RequestBody String account){
        if(adminUserService.deleteAdminUser(account)){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @PutMapping("/adminUser/{account}")
    public Msg resetPassword(@PathVariable("account") String account ,@RequestBody String password){
        if(adminUserService.resetAdminUserPassword(account,password)){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @GetMapping("/queryAdminUsers")
    public Msg fuzzyQueryAdminUser(@Param("account") String account, @Param("username")  String username, @Param("email") String email, @Param("phone") String phone){
        return Msg.success().add("result",adminUserService.fuzzyQueryAdminUser(account,username,email,phone));
    }


    @GetMapping("/adminUser/username")
    public Msg getAdminUsername(HttpServletRequest httpRequest) throws ShiroException{
        String username = adminUserService.getUsernameByToken(httpRequest);
        return Msg.success().add("username", username);
    }

}
