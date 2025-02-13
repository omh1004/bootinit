package com.bs.basicboot.common.config.properties;

//프로퍼티에 작성된 데이터를 확인하는 클래스

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Getter
@ConfigurationProperties("my")
public class MyDataProperties {
    private final String ip;
    private final String port;


    public MyDataProperties(String ip, String port) {

        String reg="^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$";
        if(Pattern.matches(reg,ip)){
            this.ip=ip;
        }else{
            throw new IllegalArgumentException("my.ip 데이터가 아이피주소형식이 아닙니다.");
        }

        this.port = port;
    }



}
