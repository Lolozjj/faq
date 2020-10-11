package com.zjj.faq.controller.organization.request;

import lombok.Data;

/**
 * @author 阿呆的小鸡仔
 */
@Data
public class CreateOrgRequest {
    protected String name;
    protected String describe;
    protected String address;
}
