package com.cck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //设置扫描接口所在包
                .apis(RequestHandlerSelectors.basePackage("com.cck.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        //swagger文档标题
                        .title("newspaper后端API")
                        .description("newspaper后端API,详细信息...")
                        .version("1.1")
                        .contact(new Contact("cck1718","https://cck1718.github.io","1229584275@qq.com"))

                        /*
                        设置接口规范
                        .license("")
                        .licenseUrl("")*/

                        .build());
    }
}
