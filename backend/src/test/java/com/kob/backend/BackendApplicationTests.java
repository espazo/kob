package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        System.out.println(bCryptPasswordEncoder.encode("pb"));
        System.out.println(bCryptPasswordEncoder.encode("pc"));
        System.out.println(bCryptPasswordEncoder.encode("pd"));
        System.out.println(bCryptPasswordEncoder.encode("pe"));

    }

}
