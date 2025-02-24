package com.bs.basicboot.common.config;

import com.bs.basicboot.common.MyAccessDenied;
import com.bs.basicboot.common.token.JwtTokenFilter;
import com.bs.basicboot.security.model.service.DBConnectionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //시큐리티 설정은 securityfilter를 bean으로 등록

    private final DBConnectionProvider dbProvider;
    private final JwtTokenFilter tokenFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(web->web.disable())
                //interceptor-url설정과 동일
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(req->CorsUtils.isPreFlightRequest(req)).permitAll()
                        .requestMatchers("/").permitAll()
                                .requestMatchers("/**").permitAll()
                        .requestMatchers("index.html").permitAll()
                        .requestMatchers("/member/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/api-docs").permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/templates/**")).permitAll()
                        .requestMatchers("/auth/login.do").permitAll()
                       // .anyRequest().authenticated()
                )
//                .formLogin(formlogin->formlogin
//                        .loginProcessingUrl("/loginend.do")
//                        .successForwardUrl()
//                )
//                .logout(logout->logout
//                        .su
//                )
                //인증처리하는 서비스를 등록 -> DB인증 절차 처리
//                .authenticationProvider(dbProvider)
                //권한이 부족한 사용자가 서비스 접근했을때
                .exceptionHandling(handle->handle
                        .accessDeniedHandler(new MyAccessDenied())
                )
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
