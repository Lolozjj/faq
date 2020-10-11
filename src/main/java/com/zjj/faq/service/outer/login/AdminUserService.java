package com.zjj.faq.service.outer.login;

import com.github.pagehelper.PageInfo;
import com.zjj.faq.controller.adminuser.request.AdminUserAddRequest;
import com.zjj.faq.controller.adminuser.request.AdminUserModificationRequest;
import com.zjj.faq.entity.User;
import com.zjj.faq.service.outer.login.response.AdminUserResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 阿呆的小鸡仔
 */
public interface AdminUserService {

    /**
     * 获取所有管理员数据
     *
     * @param curPage  当前需要的数据是哪一页
     * @param pageSize 每页多少个数据
     * @return 管理员列表
     */
    public PageInfo<AdminUserResponse> getAllAdminUsers(int curPage, int pageSize);

    /**
     * 添加一个管理员
     *
     * @param adminUserRequest 要添加的管理员的数据
     * @return 是否添加成功
     */
    public boolean addAdminUser(AdminUserAddRequest adminUserRequest);

    /**
     * 修改一个管理员的信息
     *
     * @param adminUserModificationRequest 需要修改的参数
     * @return 是否成功修改
     */
    public boolean modificationAdminUser(AdminUserModificationRequest adminUserModificationRequest);

    /**
     * 重置管理员密码
     *
     * @param account  账号
     * @param password 密码
     * @return 重置结果
     */
    public boolean resetAdminUserPassword(String account, String password);

    /**
     * 删除管理员账号
     *
     * @param account 账号
     * @return 删除结果
     */
    public boolean deleteAdminUser(String account);

    /**
     * 按条件模糊查询
     * @param account 账号
     * @param username 用户名
     * @param email 邮箱
     * @param phone 手机
     * @return 查询结果
     */
    public List<AdminUserResponse> fuzzyQueryAdminUser(String account,String username,String email,String phone);


    /**
     * 根据登录凭证token 获得管理员用户名。
     * 解析token中的账号，然后获得用户名
     * @param httpRequest 请求
     * @return 用户名
     */
    public String getUsernameByToken(HttpServletRequest httpRequest);
}
