package com.group.event_notifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.group.event_notifier.security.Host;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Controller

@Data
@RequestMapping("/make_events")
public class HostController {
    static String folderDirectory = System.getProperty("user.dir") + "/src/main/resources/static/upload";
    private final  EventRepository eventRepo;
    @GetMapping
    public String makeEvents(Model model,Event event){  
        model.addAttribute("EVENT", event);
        return "host_home";

    }
    @PostMapping
    public String saveEvents(@RequestParam("image") MultipartFile multipartfile,  Event event,Model model,@AuthenticationPrincipal Host host) throws IOException{
        
        String imageValue = multipartfile.getOriginalFilename();
        Path file = Paths.get(folderDirectory, imageValue);
        event.setImgDirectory(imageValue); 
        event.setOrganizationId(host.getId());
        eventRepo.save(event); 
       
        Files.write(file, multipartfile.getBytes());

       return "redirect:/make_events";


    }

    
}
