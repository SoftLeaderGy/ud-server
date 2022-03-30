package com.yang.udfile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang.udfile.dao")
public class UdFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdFileApplication.class, args);
    }

}
