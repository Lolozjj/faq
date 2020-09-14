package com.zjj.faq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author chen
 * @date 2019/7/24
 * @email 15218979950@163.com
 * @description Swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zjj.faq"))
                .paths(PathSelectors.regex("/.*"))
                //.paths(PathSelectors.regex("/user/.*"))
                //.paths(PathSelectors.regex("/group/.*"))
                .build();
    }


    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("faq")
                //创建人
                .contact(new Contact("郑俊杰", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("使用SpringBoot整合各种框架")
                .build();
    }

    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey("token", "token", "header")
        );
    }
}