package com.group.event_notifier;


import org.springframework.stereotype.Controller;


import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;



@Data
@Controller

public class HomeController {
    @GetMapping()
    public String getMethodName() {
        
        return "home";
    }
    
    
}
