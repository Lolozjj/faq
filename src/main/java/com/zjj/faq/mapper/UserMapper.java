package com.zjj.faq.mapper;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.Role;
import com.zjj.faq.entity.User;
import com.zjj.faq.service.outer.login.response.AdminUserResponse;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ：zjj
 * @description： 操作user表
 * @date ：2020/9/8 0008 8:03
 */
@Service
public interface UserMapper extends Mapper<User> {

    /**
     * 根据账号获取用户id
     * @param account 账号
     * @return id
     */
    public Long getIdByAccount(String account);
    /**
     * 获取用户权限
     * @param account 账号
     * @return 权限列表
     */
    public List<Permission> getPermissions(String account);

    /**
     * 获取账号角色
     * @param account 账号
     * @return 角色列表
     */
    public List<Role> getRoles(String account);
    /**
     * 获取密码
     * @param account 账号
     * @return 加密密码
     */
    public String getPassword(String account);

    /**
     * 根据邮箱获取账号
     * @param email 邮箱
     * @return 账号
     */
    public String getAccountByEmail(String email);

    /**
     * 根据手机号获取账号
     * @param phone 手机号
     * @return 账号
     */
    public String getAccountByPhone(String phone);

    /**
     * 获取账号状态
     * @param account 账号
     * @return 账号状态
     */
    public int getStateByAccount(String account);

    /**
     * 根据账号更改
     * @param user 更改参数
     * @return 改变数据条数
     */
    public int updateUserByAccount(User user);

    /**
     * 更改管理员密码密码
     * @param account 账号
     * @param password 密码
     * @return 影响行数
     */
    public int updatePasswordByAccount(String account,String password);

    /**
     * 模糊查询管理员数据
     * @param account 账号
     * @param username 用户名
     * @param email 邮箱
     * @param phone 手机
     * @return 查询的数据
     */
    public List<AdminUserResponse> fuzzyQueryAdminUser(String account, String username, String email, String phone);

    /**
     * 通过账号获得用户名
     * @param account 账号
     * @return 用户名
     */
    public String getUsernameByAccount(String account);
    /**
     * 加密密码
     * @param password 明文密码
     * @return 密文密码
     */
    public default String encryption(String password){
        Object result = new SimpleHash(
                "MD5",
                password,
                ByteSource.Util.bytes(2000+""),
                2
        );//通过SimpleHash得到MD5加密过的密文密码，此处要与UserRealm的认证，以及ShiroConfig的HashedCredentialsMatcher配合使用
        return result.toString();
    }

}
