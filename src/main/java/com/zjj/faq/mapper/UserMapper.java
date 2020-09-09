package com.zjj.faq.mapper;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/8 0008 8:03
 */
public interface UserMapper extends Mapper<User> {
    public List<Permission> getPermissions(String name);

    public String getPassword(String name);
}
