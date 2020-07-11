package com.example.attendance_sheet.Repository;

import java.util.Optional;

import com.example.attendance_sheet.Entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    
    Optional<UserEntity> findByEmail(String email);
}