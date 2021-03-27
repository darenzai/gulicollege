package com.darenzai.mybatis_plus;

import com.darenzai.mybatis_plus.entity.User;
import com.darenzai.mybatis_plus.mapper.UserMapper;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@MapperScan("com.darenzai.mybatis_plus.mapper")
@SpringBootApplication
public class MybatisPlusApplication {



    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);

    }





}
