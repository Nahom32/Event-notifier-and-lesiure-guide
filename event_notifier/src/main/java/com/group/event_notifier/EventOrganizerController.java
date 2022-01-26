package com.group.event_notifier;

import java.util.ArrayList;
import java.util.List;

import com.group.event_notifier.security.Host;
import com.group.event_notifier.security.HostRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@Data
@RequestMapping("/organizer")
public class EventOrganizerController {
  private final HostRepository hostRepo;
  @GetMapping
  public String getOrganizer(Model model){

    List<Host> organizers=new  ArrayList<>();
    hostRepo.findAll().forEach(i->{
      if(i.isOrganization()){
        organizers.add(i);
      }
    });
    model.addAttribute("ORGANIZERS", organizers);

    return "organizerList";
  }

  

  @GetMapping("/detail/{id}")
  public String getOrganizerDetail(@PathVariable("id") Long id,Model model){
  Host host=hostRepo.findById(id).get();
  model.addAttribute("HOST", host);
    return "organizer";
  }
  

}
