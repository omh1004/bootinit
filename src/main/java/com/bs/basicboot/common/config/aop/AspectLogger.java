package com.bs.basicboot.common.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AspectLogger {

    // 어떤 특정메소드 시작전에 실행하는 로직 설정가능
    @Before("execution(* com.bs.basicboot..*(..))")
    public void beforeLog(JoinPoint jp){
        log.warn("==== before log aop===");
        Signature signature = jp.getSignature();
        log.warn(signature.getDeclaringTypeName()+" "+signature.getName());
        log.warn("====----===");

    }
}
