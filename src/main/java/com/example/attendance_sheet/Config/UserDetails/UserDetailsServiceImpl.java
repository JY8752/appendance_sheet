package com.example.attendance_sheet.Config.UserDetails;

import com.example.attendance_sheet.Common.UserRole;
import com.example.attendance_sheet.Common.Exception.UserRoleNotFoundException;
import com.example.attendance_sheet.Entity.UserEntity;
import com.example.attendance_sheet.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userEntity = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
        
        UserRole role = UserRole.getUserRoleById(userEntity.getRole())
            .orElseThrow(() -> new UserRoleNotFoundException("ロールが見つかりません"));

        return new UserDetails(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getName(), role);
    }

    @Override
    public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));

        UserRole role = UserRole.getUserRoleById(userEntity.getRole())
            .orElseThrow(() -> new UserRoleNotFoundException("ロールが見つかりません"));

        return new UserDetails(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getName(), role);
    }

    @Override
    public void login(int id) {
        UserDetails userDetails = this.loadUserById(id);
        if(userDetails == null) {
            return;
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null);
        token.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}