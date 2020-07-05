package com.example.attendance_sheet.Config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    //パスワードエンコーダー
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //データソース
    @Autowired
    private DataSource dataSource;

    //ユーザIDとパスワードを取得するSQL
    private static final String USER_SQL = "select"
        + " user_id"
        + ", password"
        + ", true"
        + " from m_user"
        + " where user_id = ?";
    
    //ユーザのロールを取得するSQL
    private static final String ROLE_SQL = "select"
        + " user_id"
        + ", role"
        + " from m_user"
        + " where user_id = ?";
        
    @Override
    public void configure(WebSecurity web) throws Exception {
        //静的リソースへのセキュリティー除外
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .usernameParameter("userId")//ログインページのユーザーID
                .passwordParameter("password")//ログインページのパスワード
                .defaultSuccessUrl("/home", true);//ログイン成功時の遷移先
                //ログアウト処理
                http
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(USER_SQL)
            .authoritiesByUsernameQuery(ROLE_SQL)
            .passwordEncoder(passwordEncoder());
    }
}