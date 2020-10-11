package com.zjj.faq.mapper;

import com.zjj.faq.entity.Organization;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 阿呆的小鸡仔
 */
@Service
public interface OrganizationMapper extends Mapper<Organization> {

    /**
     * 新增一个组织
     * @param organization 组织
     * @return 影响行数
     */
    public int add(Organization organization);

    /**
     * 获取某个用户创建的组织
     * @param id id
     * @return 组织列表
     */
    public List<Organization> getOrganizationByCreator(Long id);

    /**
     * 加入组织用户对应表
     * @param userId 用户
     * @param organizationId 加入的组织
     * @param identity 身份
     * @return 影响行数
     */
    public int addUserToOrganization(Long userId,Long organizationId,Integer identity);

    /**
     * 组织的成员数加一
     * @param organizationId 组织id
     * @return 影响行数
     */
    public int organizationNumUp(Long organizationId);

    /**
     * 在组织用户对应表种删除某组织的所有记录
     * @param id 组织id
     * @return 影响行数
     */
    public int deleteOrganizationOnUserToOrganization(Long id);


    /**
     * 根据id或名称查询组织
     * @param idOrName id或名称
     * @return 查询结果
     */
    public List<Organization> searchOrganizationByIdOrName(String idOrName);

    /**
     * 获取用户加入的组织，不包括自己创建的
     * @param userId 用户id
     * @return 加入的组织
     */
    public List<Organization> getWithOrganizationJoin(Long userId);
}
