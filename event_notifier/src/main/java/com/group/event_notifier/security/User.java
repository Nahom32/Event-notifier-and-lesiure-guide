package com.group.event_notifier.security;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Enter your full name please")
    private String fullname;

    @NotBlank(message = "Enter your date of birth ")
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String dateOfBirth;

    @Column(unique = true)
    @NotBlank(message = "Enter your username ")
    private String username;

    
    @NotBlank(message = "Enter your password")
    private String password;

    @NotBlank(message = "Enter your email")
    @Email(message = "Please insert a valid email")
    private String email;

    @NotBlank(message = "Enter your streetAddress")
    private String streetAddress;
    
    
    @NotBlank(message = "Enter your phone number")
    private String phone;

    
    //@NotBlank(message = "Select a choice please")
    private String choice1;


    //@NotBlank(message = " choice please")
    private String choice2;

   // @NotBlank(message = " choice please")
    private String choice3;

    //@NotBlank(message = " choice please")
    private String choice4;

    //@NotBlank(message = " choice please")
    private String choice5;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
