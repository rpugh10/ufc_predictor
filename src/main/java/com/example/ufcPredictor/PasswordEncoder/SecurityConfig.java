package com.example.ufcPredictor.PasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // important for fetch POSTs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", 
                    "/login.css",
                    "/login.html",
                    "/createAccount.html",
                    "/createAccount.css",
                    "/main.html",
                    "/main.css",
                    "/login.js",
                    "/createAccount.js",
                    "/users/create",
                    "/users/login",
                    "/predict",
                    "/ufc-background.jpg",
                    "/main.js"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable()); // disable default login page

        return http.build();
    }
    
}
