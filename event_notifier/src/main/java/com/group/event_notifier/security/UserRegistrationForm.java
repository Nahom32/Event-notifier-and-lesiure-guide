package com.group.event_notifier.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class UserRegistrationForm {
    private String fullname;
    private String dateOfBirth;
    private String username;
    private String password;
    private String email;
    private String streetAddress;
    private String phone;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

    User toUser(PasswordEncoder encoder) {
        User user = new User();
        user.setFullname(this.fullname);
        user.setDateOfBirth(this.dateOfBirth);
        user.setUsername("@user-"+this.username);
        user.setPassword(encoder.encode(this.password));
        user.setEmail(this.email);
        user.setStreetAddress(this.streetAddress);
        user.setPhone(this.phone);
        user.setChoice1(this.choice1);
        user.setChoice2(this.choice2);
        user.setChoice3(this.choice3);
        user.setChoice4(this.choice4);
        user.setChoice5(this.choice5);
        return user;

    }
}