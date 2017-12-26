package com.anxpp;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    @Resource
    private Test test;

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
//        while (true) {
//            Thread.currentThread().sleep(1000);
//        }
    }

    @Bean
    CommandLineRunner start() {
        return args -> test.print();
    }
}
