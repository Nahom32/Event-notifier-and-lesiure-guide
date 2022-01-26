package com.group.event_notifier.security;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup/admin")
public class AdminRegistrationController {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String adminRegisterForm(){
        return "Admin-signup";
    }

    @PostMapping
    public String adminProcessRegistration(AdminRegistrationForm adminForm) {
        adminRepository.save(adminForm.toAdmin(passwordEncoder));
        return "redirect:/admin";
    }

}

