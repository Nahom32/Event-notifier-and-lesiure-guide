package com.group.event_notifier.security;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup/user")
public class UserRegistrationController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String userRegisterForm(Model model) {
        model.addAttribute("USER", new User());
        return "User-Signup";
    }

    @PostMapping
    public String userProcessRegistration(UserRegistrationForm userForm,
     @Valid @ModelAttribute("USER") User user,BindingResult bindingResult, Model model) {
         if(bindingResult.hasErrors()){
             model.addAttribute("USER", user);
             return "User-Signup";
         }
         model.addAttribute("USER", user);
        userRepository.save(userForm.toUser(passwordEncoder));
        return "redirect:/mainPage";
    }
}
