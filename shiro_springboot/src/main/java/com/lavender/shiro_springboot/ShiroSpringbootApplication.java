package com.lavender.shiro_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure   .SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lavender.shiro_springboot.mapper")
public class ShiroSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringbootApplication.class, args);
    }

}
