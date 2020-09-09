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
    @Id                         //需要指定主键
    @Column(name = "id")
    protected Long id; //主键
    protected String username; //姓名
    protected String email; //邮箱
    protected String phone; //手机号
    protected String account; //账号
    protected String password; //密码
    protected Integer state; //状态
    protected String salt; //密码加密的盐值
    protected List<Role> roles; //角色

}
