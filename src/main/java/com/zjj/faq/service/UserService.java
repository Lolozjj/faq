package com.zjj.faq.service;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.Role;
import com.zjj.faq.entity.User;

import java.util.List;

/**
 * @author 阿呆的小鸡仔
 */
public interface UserService {

    /**
     * 获取密码
     * @param email 邮箱
     * @return 密码
     */
    public String getPassword(String email);

    /**
     * 获得账号状态
     * @param name 姓名
     * @return 状态
     */
    public Integer getState(String name);

    /**
     * 获取账户角色
     * @param name 姓名
     * @return 角色列表
     */
    public List<Role> getRoles(String name);

    /**
     * 获取账户权限
     * @param name 姓名
     * @return 权限列表
     */
    public List<Permission> getPermission(String name);

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
