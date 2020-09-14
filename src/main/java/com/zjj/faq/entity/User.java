package com.zjj.faq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/7 0007 20:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    /**
     * 主键
     */
    protected Long id;
    /**
     * 姓名
     */
    protected String username;
    /**
     * 邮箱
     */
    protected String email;
    /**
     * 手机号
     */
    protected String phone;
    /**
     * 账号
     */
    protected String account;
    /**
     * 密码
     */
    protected String password;
    /**
     * 账号状态(1正常2异常3冻结4封号)
     */
    protected Integer state;
    /**
     * 加密盐值
     */
    protected String salt;
    /**
     * 角色列表
     */
    protected List<Role> roles;

}
