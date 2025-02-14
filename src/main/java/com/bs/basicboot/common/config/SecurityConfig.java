package com.bs.basicboot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //시큐리티 설정은 securityfilter를 bean으로 등록

    //시큐리티 필터체인을 등록해주면, 설정이 자동으로 들어온다.

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
             // 요청마다 indexhashcode를 보내줘야하는데, 아래 내용을 설정하면, 안해도된다.
         return    http
                    .csrf(web->{
                        web.disable();
                    })
                 //interceptor-url 설정과 동일
                 // http요청에 있는것을 권한 확인 하겠다.
                 .authorizeHttpRequests(registry-> registry
                            .requestMatchers("/").permitAll()
                             .requestMatchers("/WEB-INF/views/**").permitAll()
                             // 어떤 요청이던 요청받겠다.
                             .anyRequest().authenticated())
//                 .formLogin(formlogin->formlogin
//                         //로그인 처리하는 url 주소  ,
//                         .loginProcessingUrl("login.do")
//                            .successForwardUrl()
//                 )
//                 .logout(logout->logout)
                 //인증처리하는 서비스를 등록 ->DB 인증 절차 처리
                 .authenticationProvider()
                 .build();
    }
}
