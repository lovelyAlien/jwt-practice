package com.security.jwtpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService users() {


        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, admin);

    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin(config -> {
                    config.loginPage("/login")
                            .successForwardUrl("/")
                            .failureForwardUrl("/login?error=true");
                });
    }
}
