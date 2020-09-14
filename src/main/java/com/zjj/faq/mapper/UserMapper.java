package com.zjj.faq.mapper;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.User;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/8 0008 8:03
 */
@Service
public interface UserMapper extends Mapper<User> {
    /**
     * 获取用户权限
     * @param email 姓名
     * @return 权限列表
     */
    public List<Permission> getPermissions(String email);

    /**
     * 获取密码
     * @param email 姓名
     * @return 加密密码
     */
    public String getPassword(String email);
}
