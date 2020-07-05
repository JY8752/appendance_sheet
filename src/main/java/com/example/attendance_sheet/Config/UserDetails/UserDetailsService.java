package com.example.attendance_sheet.Config.UserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService{

    /**
     * idと一致するアカウント情報をUserDetailsにセットする。
     * @param id
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByUsername(Integer id) throws UsernameNotFoundException;

    void login(int id);

    void logout();
}