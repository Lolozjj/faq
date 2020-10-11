package com.zjj.faq.service.outer.organization;

import com.github.pagehelper.Page;
import com.zjj.faq.batis.shiro.JwtUtil;
import com.zjj.faq.controller.organization.request.CreateOrgRequest;
import com.zjj.faq.entity.Organization;
import com.zjj.faq.entity.User;
import com.zjj.faq.mapper.OrganizationMapper;
import com.zjj.faq.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 阿呆的小鸡仔
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationMapper organizationMapper;
    private final UserMapper userMapper;

    public OrganizationServiceImpl(OrganizationMapper organizationMapper, UserMapper userMapper) {
        this.organizationMapper = organizationMapper;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean createOrganization(CreateOrgRequest createOrgRequest, HttpServletRequest httpRequest) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(createOrgRequest, organization);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        organization.setCreateDate(date);
        String token = httpRequest.getHeader("token");
        String account = JwtUtil.getAccount(token);
        Long creatorId = userMapper.getIdByAccount(account);
        User creator = new User();
        creator.setId(creatorId);
        organization.setCreator(creator);
        int result=0 ;
        try {
            result = organizationMapper.add(organization);
            organizationMapper.addUserToOrganization(creatorId,organization.getId(),0);
        }catch (Exception e){
            e.printStackTrace();
            /*事务回滚*/
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result==1;
    }

    @Override
    public List<Organization> getOrganization(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("token");
        String account = JwtUtil.getAccount(token);
        Long creatorId = userMapper.getIdByAccount(account);
        return organizationMapper.getOrganizationByCreator(creatorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOrganization(Long id) {
        Organization organization = new Organization();
        organization.setId(id);
        try {
            organizationMapper.deleteOrganizationOnUserToOrganization(id);
            return organizationMapper.delete(organization)==1;
        }catch (Exception e){
            e.printStackTrace();
            /*事务回滚*/
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public List<Organization> searchOrganization(String idOrName) {

        return organizationMapper.searchOrganizationByIdOrName(idOrName);
    }

    /**
     * 感觉代码会出  bug
     * @param organizationId 组织id
     * @param request 通过请求获取token 解析当前用户
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean joinOrganization(Long organizationId, HttpServletRequest request) {
        String token = request.getHeader("token");
        String account = JwtUtil.getAccount(token);
        Long userId = userMapper.getIdByAccount(account);
        int result=0;
        try {
            int row=organizationMapper.organizationNumUp(organizationId);
            if(row==1){
                result=organizationMapper.addUserToOrganization(userId, organizationId, 1);
            }
        }catch (Exception e){
            e.printStackTrace();
            /*事务回滚*/
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return result==1;
    }

    @Override
    public List<Organization> getWithOrganizationJoin(HttpServletRequest request) {
        String token = request.getHeader("token");
        String account = JwtUtil.getAccount(token);
        Long userId = userMapper.getIdByAccount(account);
        return organizationMapper.getWithOrganizationJoin(userId);
    }
}
