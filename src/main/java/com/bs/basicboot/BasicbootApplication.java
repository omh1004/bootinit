package com.bs.basicboot;

import com.bs.basicboot.common.config.properties.MyDataProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Properties;

@Order(5)
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(MyDataProperties.class)
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

       //SpringApplication.run(BasicbootApplication.class, args);

        springApplication.run("데마시아아아아아아아아");
    }

}
