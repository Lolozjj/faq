package com.zjj.faq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/7 0007 20:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name; 
    /**
     * 拥有的权限集合
     */
    private List<Permission> permissions;
}
