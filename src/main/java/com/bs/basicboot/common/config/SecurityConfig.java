package com.bs.basicboot.common.config;

import com.bs.basicboot.common.MyAccessDenied;
import com.bs.basicboot.common.token.JwtTokenFilter;
import com.bs.basicboot.security.model.service.DBConnectionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final DBConnectionProvider dbProvider;
    private final JwtTokenFilter tokenFilter;

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
                             .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                         .requestMatchers("/auth/login.do").permitAll()
                             // 어떤 요청이던 요청받겠다.
                             .anyRequest().authenticated())
//                 .formLogin(formlogin->formlogin
//                         //로그인 처리하는 url 주소  ,
//                         .loginProcessingUrl("login.do")
//                            .successForwardUrl()
//                 )
//                 .logout(logout->logout)
                 //인증처리하는 서비스를 등록 ->DB 인증 절차 처리
                // .authenticationProvider(dbProvider)

                 //권한이 부족한 사용자가 서비스 접근했을때, 처리할 로직 처리
                 .exceptionHandling(handle->{handle
                         .accessDeniedHandler(new MyAccessDenied());
                 })
                 .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                 .build();
    }
}
