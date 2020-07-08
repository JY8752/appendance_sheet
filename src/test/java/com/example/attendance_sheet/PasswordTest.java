package com.example.attendance_sheet;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Transactional
public class PasswordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        System.out.println();
        System.out.println(passwordEncoder.encode("password"));
        assertThat(passwordEncoder.matches("password", "$2a$10$qh0Z0u5XYLwWZCmDY6qtR.z8MDFm3sMCPxt6qU.DdoLcUviOQ2sDq"), is(true));
    }
    
}