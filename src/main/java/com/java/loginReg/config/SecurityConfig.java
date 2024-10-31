package com.java.loginReg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // CSRF korumasını devre dışı bırak
            .authorizeRequests()
                .antMatchers(
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/v2/api-docs/**",
                    "/webjars/**",
                    "/**" // Tüm diğer isteklere izin ver
                ).permitAll() // Bu URL'lere erişime izin ver
                .anyRequest().authenticated(); // Diğer tüm isteklere kimlik doğrulama gerektir
    }
}
