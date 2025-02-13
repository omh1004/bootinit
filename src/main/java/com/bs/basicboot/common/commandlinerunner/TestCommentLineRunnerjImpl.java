package com.bs.basicboot.common.commandlinerunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Slf4j
@Component
public class TestCommentLineRunnerjImpl implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("@Component 선언한 run()메소드 ");
    }
}
