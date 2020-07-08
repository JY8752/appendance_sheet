package com.example.attendance_sheet.Repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.example.attendance_sheet.Entity.UserEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        UserEntity entity = new UserEntity();
        entity.setCreatedAt(LocalDateTime.now());
        entity.setEmail("test");
        entity.setName("test");
        entity.setPassword("pass");
        entity.setPasswordLastChanged(null);
        entity.setPasswordLastReset(null);
        entity.setUpdatetdAt(null);
        entity.setRole("test");
        userRepository.save(entity);

        UserEntity actual = userRepository.findById(entity.getId()).get();

        assertThat(actual.getId()).isEqualTo(entity.getId());
        assertThat(actual.getCreatedAt()).isEqualTo(entity.getCreatedAt());
        assertThat(actual.getEmail()).isEqualTo(entity.getEmail());
        assertThat(actual.getName()).isEqualTo(entity.getName());
        assertThat(actual.getPassword()).isEqualTo(entity.getPassword());
        assertThat(actual.getPasswordLastChanged()).isNull();
        assertThat(actual.getPasswordLastReset()).isNull();
        assertThat(actual.getUpdatetdAt()).isNull();
        assertThat(actual.getRole()).isEqualTo(entity.getRole());

    }
    
}