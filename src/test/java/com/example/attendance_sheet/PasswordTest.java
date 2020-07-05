package com.example.attendance_sheet;

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
        System.out.println(passwordEncoder.encode("password"));
    }
    
}