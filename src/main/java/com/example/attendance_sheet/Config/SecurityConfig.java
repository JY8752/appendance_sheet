package com.example.attendance_sheet.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.attendance_sheet.Config.UserDetails.UserDetailsService;
import com.example.attendance_sheet.Config.UserDetails.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

        
    @Override
    public void configure(WebSecurity web) throws Exception {
        //静的リソースへのセキュリティー除外
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.setSharedObject(HttpSessionSecurityContextRepository.class, new HttpSessionSecurityContextRepository());

        http.
            authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
                //ログイン処理
                http
                .formLogin()
                .loginProcessingUrl("/login")//ログイン処理のパス
                .loginPage("/login")//ログインページの指定
                .failureUrl("/login")//ログイン失敗時の遷移先
                .usernameParameter("email")//ログインページのユーザーID
                .passwordParameter("password")//ログインページのパスワード
                .defaultSuccessUrl("/home", true);//ログイン成功時の遷移先
                //ログアウト処理
                http
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new SecurityContextRepository(){
        
            @Override
            public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
                // TODO Auto-generated method stub
                
            }
        
            @Override
            public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
                // TODO Auto-generated method stub
                return null;
            }
        
            @Override
            public boolean containsContext(HttpServletRequest request) {
                // TODO Auto-generated method stub
                return false;
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}