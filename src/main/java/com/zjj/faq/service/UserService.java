package com.zjj.faq.service;

import com.zjj.faq.entity.Permission;
import com.zjj.faq.entity.Role;
import com.zjj.faq.entity.User;

import java.util.List;

public interface UserService {
    public String getPassword(String name);

    public Integer getState(String name);

    public List<Role> getRoles(String name);

    public List<Permission> getPermission(String name);

    public String encryption(String password);

    public String add(User user);
}
