package com.bs.basicboot;

import com.bs.basicboot.common.MyBanner;
import com.bs.basicboot.common.config.properties.MyDataProperties;
import com.bs.basicboot.model.dto.Member;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

@Order(5)
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(MyDataProperties.class)
@ServletComponentScan
public class BasicbootApplication implements CommandLineRunner {

    @Autowired
    private WebApplicationContext context;


    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner 메소드 실행 ");
//        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        Environment env=  context.getBean(Environment.class);
        System.out.println(env);
    }

    public static void main(String[] args) {
        //부트 프로그램 시작하는 곳
        //시작할때 , Properties를 설정해 줄 수 있음
        Properties applicationProperties = new Properties();
        applicationProperties.setProperty("server.port","9099");

        //SpringApplication 클래스에서 제공하는 setDefaultProperties() 메소드를 이용
        SpringApplication springApplication = new SpringApplication(BasicbootApplication.class);
        springApplication.setDefaultProperties(applicationProperties);
        //banner설정하기
        springApplication.setBanner(new MyBanner());
        springApplication.setBannerMode(Banner.Mode.LOG);

       //SpringApplication.run(BasicbootApplication.class, args);

        Member member = Member.builder().userId("bs")
                .password("1234")
                .birthday(LocalDate.of(1922,2,15))
                .build();

        //유효성검사할 객체를 생성
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //유효성 검사하기
        Set<ConstraintViolation<Member>> result =  validator.validate(member);
        result.forEach(cons->{
            log.warn("필드 : {}",cons.getPropertyPath().toString());
            log.warn("대상값 : {}",cons.getInvalidValue());
            log.warn("메세지 : {}",cons.getMessage());
        });

        Member m2 = Member.builder().userId("bslove")
                .password("1234abcE")
                .birthday(LocalDate.of(1922,2,15))
                .build();


        result =  validator.validate(m2);
        result.forEach(cons->{
            log.warn("--------------------------");
            log.warn("대상값1 : {}",cons.getInvalidValue());
            log.warn("필드1 : {}",cons.getPropertyPath().toString());
            log.warn("메세지1 : {}",cons.getMessage());
        });
        springApplication.run("데마시아아아아아아아아");



    }

}
