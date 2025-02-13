package com.bs.basicboot.common.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@MapperScan("com.bs.basicboot.model.dao")
public class WebMvcConfig {

    @Bean
    Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
