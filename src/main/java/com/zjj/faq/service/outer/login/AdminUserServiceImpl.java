package com.zjj.faq.service.outer.login;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjj.faq.batis.shiro.JwtUtil;
import com.zjj.faq.controller.adminuser.request.AdminUserAddRequest;
import com.zjj.faq.controller.adminuser.request.AdminUserModificationRequest;
import com.zjj.faq.entity.User;
import com.zjj.faq.mapper.UserMapper;
import com.zjj.faq.service.outer.login.response.AdminUserResponse;
import org.apache.shiro.ShiroException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author 阿呆的小鸡仔
 */
@Service
public class  AdminUserServiceImpl implements AdminUserService {

    private final UserMapper userMapper;

    public AdminUserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageInfo<AdminUserResponse> getAllAdminUsers(int curPage,int pageSize) {
        PageHelper.startPage(curPage,pageSize);
        User ex =new User();
        ex.setState(-1);
        List<User> users = userMapper.select(ex);
        List<AdminUserResponse> adminUserResponses = users.stream().map(user -> {
            AdminUserResponse adminUserResponse = new AdminUserResponse();
            BeanUtils.copyProperties(user, adminUserResponse);
            return adminUserResponse;
        }).collect(Collectors.toList());
        return new PageInfo<AdminUserResponse>(adminUserResponses);
    }

    @Override
    public boolean addAdminUser(AdminUserAddRequest adminUserRequest) {
        User user=new User();
        BeanUtils.copyProperties(adminUserRequest,user);
        user.setAccount(randomAccount());
        user.setPassword(userMapper.encryption(user.getPassword()));
        user.setState(-1);
        user.setSalt("2000");
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean modificationAdminUser(AdminUserModificationRequest adminUserModificationRequest) {
        User user=new User();
        BeanUtils.copyProperties(adminUserModificationRequest, user);
        return userMapper.updateUserByAccount(user)==1;
    }

    @Override
    public boolean resetAdminUserPassword(String account, String password) {
        return userMapper.updatePasswordByAccount(account,userMapper.encryption(password))==1;
    }

    @Override
    public boolean deleteAdminUser(String account) {
        User user = new User();
        user.setAccount(account);
        return  userMapper.delete(user)==1;
    }

    @Override
    public List<AdminUserResponse> fuzzyQueryAdminUser(String account, String username, String email, String phone) {
        return userMapper.fuzzyQueryAdminUser(
                fuzzyQueryBeforeTheSuffix(account),
                fuzzyQueryBeforeTheSuffix(username),
                fuzzyQueryBeforeTheSuffix(email),
                fuzzyQueryBeforeTheSuffix(phone)
        );
    }

    @Override
    public String getUsernameByToken(HttpServletRequest httpRequest)  {

        String token = httpRequest.getHeader("token");
        if(token==null){
            throw new ShiroException("没有找到登录信息");
        }
        String account = JwtUtil.getAccount(token);
        String username = userMapper.getUsernameByAccount(account);
        if(username==null){
            throw new ShiroException("token 失效");
        }
        return username;
    }


    /**
     * 给字段前后加上%通配符，方便进行模糊查询
     * @param target 目标字符串
     * @return 加上%后的字符串
     */
    private String fuzzyQueryBeforeTheSuffix(String target){
        return "%"+target+"%";
    }

    /**
     * 随机生成账号
     * @return 生成的账号
     */
    private String randomAccount(){
        int verifySize=10;
        String sources="1234567890abcdef";
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}
