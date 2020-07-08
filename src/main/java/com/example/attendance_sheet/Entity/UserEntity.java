package com.example.attendance_sheet.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", precision = 10, nullable = false, unique  = true)
    private Integer id;

    /** emailプロパティ */
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    /** roleプロパティ */
    @Column(name = "role", length = 50, nullable = false)
    private String role;

    /** nameプロパティ */
    @Column(name = "user_name", length = 50, nullable = false)
    private String name;

    /** passwordプロパティ */
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    /** password_last_changedプロパティ */
    @Column(name = "password_last_changed", nullable = true)
    private LocalDateTime passwordLastChanged;

    /** password_last_resetプロパティ */
    @Column(name = "password_last_reset", nullable = true)
    private LocalDateTime passwordLastReset;

    /** updated_atプロパティ */
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatetdAt;

    /** created_atプロパティ */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}