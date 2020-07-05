package com.example.attendance_sheet.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    /** idプロパティ */
    private Integer id;

    private String email;

    private String name;

    private String password;

    private LocalDateTime passwordLastChanged;

    private LocalDateTime passwordLastReset;

    private LocalDateTime updatetdAt;

    private LocalDateTime createdAt;
}