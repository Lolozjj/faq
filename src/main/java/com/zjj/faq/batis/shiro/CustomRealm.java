package com.zjj.faq.batis.shiro;



import com.zjj.faq.entity.Role;
import com.zjj.faq.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chen
 * @date 2019/7/23
 * @email 15218979950@163.com
 * @description  自定义Realm,实现Shiro安全认证
 */

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }

        /* 以下数据库查询可根据实际情况，可以不必再次查询，这里我两次查询会很耗资源
         * 我这里增加两次查询是因为考虑到数据库管理员可能自行更改数据库中的用户信息
         */
        String password = userService.getPassword(username);
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        int ban = userService.getState(username);
        if (ban == 1) {
            throw new AuthenticationException("该用户已被封号！");
        }
        return new SimpleAuthenticationInfo(token, token, ByteSource.Util.bytes(2000+""),"CustomRealm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //获得该用户角色
//        String role = userService.getRoles(username);
//        //每个角色拥有默认的权限
//        String rolePermission = userService.getRolePermission(username);
//        //每个用户可以设置新的权限
//        String permission = userService.getPermission(username);
//
//        Set<String> roleSet = new HashSet<>();
//        Set<String> permissionSet = new HashSet<>();
//        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
//        roleSet.add(role);
//        permissionSet.add(rolePermission);
//        permissionSet.add(permission);
        Set<String> roleSet=new HashSet<>();
        Set<String> permissionSet=new HashSet<>();
        List<Role> roles = userService.getRoles(username);
        for(Role r: roles){
            roleSet.add(r.getName());
            permissionSet.addAll(r.getPermissions().stream().map(permission->{return permission.getName();}).collect(Collectors.toList()));
        }
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
