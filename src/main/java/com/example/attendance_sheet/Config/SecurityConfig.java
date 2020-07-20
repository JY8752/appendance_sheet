package com.example.attendance_sheet.Config;

import com.example.attendance_sheet.Config.UserDetails.UserDetailsService;
import com.example.attendance_sheet.Config.UserDetails.UserDetailsServiceImpl;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

        
    @Override
    public void configure(WebSecurity web) throws Exception {
        //静的リソースへのセキュリティー除外
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.setSharedObject(SecurityContextRepository.class, securityContextRepository());

        http.
            authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
        //ログイン処理
        http
        .formLogin()
        .loginProcessingUrl("/login")//ログイン処理のパス
        .loginPage("/login")//ログインページの指定
        .failureUrl("/login")//ログイン失敗時の遷移先
        .usernameParameter("email")//ログインページのユーザーID
        .passwordParameter("password")//ログインページのパスワード
        .defaultSuccessUrl("/home");//ログイン成功時の遷移先
        //ログアウト処理
        http
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //SecurityContentHolderにうまく格納されていない？
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new SimpleContextRepository();
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new CookieOnlySessionTrackingServletContextInitializer();
    }
}