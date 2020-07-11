package com.example.attendance_sheet.Config.UserDetails;

import java.util.Collection;

import com.example.attendance_sheet.Common.UserRole;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final Integer id;

    private  final String email;

    private  final String password;

    private  final String name;

    private final UserRole role;

    public UserDetails(Integer id, String email, String password, String name, UserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}