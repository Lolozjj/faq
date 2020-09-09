package com.zjj.faq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@tk.mybatis.spring.annotation.MapperScan("com.zjj.faq.mapper")
@EnableTransactionManagement
public class DalConfig {
} 