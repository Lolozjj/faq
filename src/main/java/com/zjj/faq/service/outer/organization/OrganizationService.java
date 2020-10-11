package com.zjj.faq.service.outer.organization;

import com.zjj.faq.controller.organization.request.CreateOrgRequest;
import com.zjj.faq.entity.Organization;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 阿呆的小鸡仔
 */
public interface OrganizationService {
    /**
     * 创建组织
     * @param createOrgRequest 创建组织需要的参数
     * @param httpRequest 请求
     * @return 是否成功
     */
    public boolean createOrganization(CreateOrgRequest createOrgRequest, HttpServletRequest httpRequest);

    /**
     * 获取登录用户创建的组织
     * @param httpRequest 请求
     * @return 组织列表
     */
    public List<Organization> getOrganization(HttpServletRequest httpRequest);
    /**
     * 删除指定id组织
     * @param id id
     * @return 是否成功
     */
    public boolean deleteOrganization(Long id);

    /**
     * 根据id或名称查询组织
     * @param idOrName id或名称
     * @return 查询结果
     */
    public List<Organization> searchOrganization(String idOrName);

    /**
     * 加入该id代表的组织
     * @param organizationId 组织id
     * @param request 通过请求获取token 解析当前用户
     * @return 加入结果
     */
    public boolean joinOrganization(Long organizationId,HttpServletRequest request);

    /**
     * 获取请求用户所加入的组织
     * @param request 请求
     * @return 加入的组织
     */
    public List<Organization> getWithOrganizationJoin(HttpServletRequest request);
}
