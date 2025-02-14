package com.bs.basicboot.common.config;

import com.bs.basicboot.common.TestInterceptor;
import com.bs.basicboot.common.config.filter.MyFilter;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableAspectJAutoProxy

@MapperScan("com.bs.basicboot.model.dao")
// WebMvcConfigurer 를 상속받아야 인터셉터를 쓸수 있다.
public class WebMvcConfig implements WebMvcConfigurer {
//
    @Bean
    FilterRegistrationBean<MyFilter> testFilter(){
        FilterRegistrationBean<MyFilter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.addUrlPatterns("/**");
        return filterFilterRegistrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/**");
    }



    @Bean
    TestInterceptor myInterceptor(){
        return new TestInterceptor();
    }


    @Bean
    Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
