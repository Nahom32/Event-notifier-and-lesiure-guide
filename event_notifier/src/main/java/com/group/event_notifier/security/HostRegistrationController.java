package com.group.event_notifier.security;


import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup/host")
public class HostRegistrationController {
    public static String credentialDirectory = System.getProperty("user.dir") + "/src/main/resources/static/credentials";
    private final HostRepository hostRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String hostRegisterForm(Model model){
        model.addAttribute("HOST", new Host());
        return "Host-signup";
    }

    @PostMapping
    public String hostProcessRegistration(HostRegistrationForm hostForm, @Valid @ModelAttribute("HOST") Host host, 
   BindingResult bindingResult,Model model) throws IOException {
       if(bindingResult.hasErrors()){
           model.addAttribute("HOST", host);
           return "Host-signup";
       }
       
        model.addAttribute("HOST", host);
        hostRepository.save(hostForm.toHost(passwordEncoder));
        return "redirect:/make_events";
       

}
}
