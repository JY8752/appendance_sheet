package com.example.attendance_sheet.Common;

import java.util.Arrays;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_ADMIN('0', "管理者ユーザー"),

    ROLE_GENERAL('1', "一般ユーザー");

    private final Character id;

    private final String name;

    public static Optional<UserRole> getUserRoleById(char id) {
        return Arrays.stream(UserRole.values())
            .filter(role -> role.getId().equals(id))
            .findFirst();
    }
    
}