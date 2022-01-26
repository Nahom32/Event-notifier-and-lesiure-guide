package com.group.event_notifier.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    
    @Bean
    PasswordEncoder bcrypPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo, HostRepository hostRepo, AdminRepository adminRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null)
                return user;
            Host host = hostRepo.findByUsername(username);
            if (host != null)
                return host;
            Admin admin = adminRepo.findByUsername(username);
            if (admin != null)
                return admin;
            throw new UsernameNotFoundException(
                 "User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        return http
                    .authorizeRequests()
                    .antMatchers("/mainPage","/mainPage/*", "/detail/*").access("hasRole('USER') or hasRole('ADMIN')")
                    .antMatchers("/make_events","/make_events/*").access("hasRole('HOST') or hasRole('ADMIN')")
                    .antMatchers("/admin","/admin/*").access("hasRole('ADMIN')")
                    .antMatchers("/organizer","/organizer/*").access("hasRole('USER') or hasRole('HOST') or hasRole('ADMIN')")
                    .antMatchers("/","**/").access("permitAll()")
                    .and()
                    .formLogin()
                        .loginPage("/signin")
                        .defaultSuccessUrl("/")
                    .and()
                    .csrf().disable()
                    .logout()
                        .logoutSuccessUrl("/")
                    .and()
                    .build();
    }}