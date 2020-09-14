package com.zjj.faq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：zjj
 * @description：TODO
 * @date ：2020/9/7 0007 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
}
