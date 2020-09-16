package com.zjj.faq.service.inter;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.Role;
import com.zjj.faq.entity.User;

import java.util.List;

/**
 * @author 阿呆的小鸡仔
 */
public interface UserService {

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
     * 根据账号获取密码
     * @param account 邮箱
     * @return 密码
     */
    public String getPassword(String account);

    /**
     * 根据账号获得账号状态
     * @param account 账号
     * @return 状态
     */
    public Integer getState(String account);

    /**
     * 根据账号获取账户角色
     * @param account 姓名
     * @return 角色列表
     */
    public List<Role> getRoles(String account);

    /**
     * 根据账号获取账户权限
     * @param account 账号
     * @return 权限列表
     */
    public List<Permission> getPermission(String account);

    /**
     * 加密密码
     * @param password 明文密码
     * @return 加密后的密码
     */
    public String encryption(String password);

    /**
     * 添加一个用户
     * @param user 用户信息
     * @return 数据库影响行数
     */
    public int add(User user);
}
