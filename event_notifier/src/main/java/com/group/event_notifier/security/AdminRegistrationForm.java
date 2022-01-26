package com.group.event_notifier.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class AdminRegistrationForm {
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String streetAddress;

    Admin toAdmin(PasswordEncoder encoder) {
        Admin admin = new Admin();
        admin.setFullname(this.fullname);
        admin.setUsername(this.username);
        admin.setPassword(encoder.encode(this.password));
        admin.setEmail(this.email);
        admin.setStreetAddress(this.streetAddress);
        return admin;
    }
}