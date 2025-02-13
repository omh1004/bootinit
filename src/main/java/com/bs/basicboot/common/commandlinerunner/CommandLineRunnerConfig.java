package com.bs.basicboot.common.commandlinerunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
public class CommandLineRunnerConfig {

    @Order(1)
    @Bean
    CommandLineRunner test(){
        return args ->{
            log.info("test메소드 실행 ");
        };
    }
    @Order(2)
    @Bean
    CommandLineRunner test1(){
        return args ->{
            log.info("test메소드2 실행 ");
        };
    }

    @Order(3)
    @Bean
    CommandLineRunner test2(){
        return args ->{
            log.info("test메소드3 실행 ");
        };
    }
}
