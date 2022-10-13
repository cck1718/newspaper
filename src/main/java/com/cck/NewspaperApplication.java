package com.cck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cck.dao")
public class NewspaperApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewspaperApplication.class,args);
    }
}
