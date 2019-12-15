package com.sumant.springboot.learning.restapitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestapitestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapitestApplication.class, args);
    }

}
