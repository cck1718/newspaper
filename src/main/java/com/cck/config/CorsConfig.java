package com.cck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许跨域请求的路径
                .addMapping("/**")
                //允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许Cookie
                .allowCredentials(true)
                //允许请求的方式
                .allowedMethods("GET","POST","DELETE","PUT")
                //允许的header属性
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(18000);
    }
}
