package com.example.book.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    // Define the SecurityFilterChain bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Start defining the security rules here
                .csrf().disable() // Disable CSRF as per your requirement
                .authorizeRequests(authz -> authz
                        .antMatchers("/api/authenticate").permitAll() // Permit authentication path
                        .anyRequest().authenticated() // All other requests require authentication
                )
                // Add your custom JWT filter here, if applicable
                // .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Stateless session for JWT use

        // Other configurations can be added here

        return http.build();
    }

    // Other beans and methods can be added here, such as AuthenticationManager, PasswordEncoder, etc.
}

