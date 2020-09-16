package com.zjj.faq.mapper;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.Role;
import com.zjj.faq.entity.User;
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
}
