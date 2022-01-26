package com.group.event_notifier;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Data;

@Controller
@Data
@RequestMapping("/events")
public class EventController {
  private final EventRepository eventRepo;
  @GetMapping()
  public String saveEventForm(Model model, Event event){
    model.addAttribute("EVENT", event);
    return "eventUpload";

  }

  @PostMapping("/event/save")
  public String saveEvent(Model model, Event event){
    model.addAttribute("EVENT", event);
    eventRepo.save(event);
    return "redirect:/profile";

  }
  
}
