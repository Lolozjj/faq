package com.zjj.faq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 组织
 * @author 阿呆的小鸡仔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "organization")
public class Organization {
    /**
     * 组织id
     */
    @Id
    @Column(name = "id")
    protected Long id;
    /**
     * 名称
     */
    protected String name;
    /**
     * 简介
     */
    protected String describe;
    /**
     * 地址
     */
    protected String address;
    /**
     * 创建时间
     */
    protected String createDate;

    /**
     * 最大人数
     */
    protected Integer max;

    /**
     * 当前人数
     */
    protected Integer num;

    /**
     * 创建者
     */
    protected User creator;


}
