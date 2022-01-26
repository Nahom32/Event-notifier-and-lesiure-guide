package com.group.event_notifier;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;

import com.group.event_notifier.security.Host;
import com.group.event_notifier.security.HostRepository;
import com.group.event_notifier.security.User;
import com.group.event_notifier.security.UserRepository;

import java.util.Date;

import lombok.Data;

@RequestMapping("/admin")
@Controller
@Data
public class AdminController {
  private final UserRepository userRepo;
  private final HostRepository hostRepo;
  private final EventRepository eventRepo;
  private final OldEventRepository oldEventRepo;
  @GetMapping
    public String adminControl(Model model){
      List<User> users =new ArrayList<>();
        userRepo.findAll().forEach(i->users.add(i));
        model.addAttribute("USERS", users);
      List<Host> uhost = new ArrayList<>(); //unapproved host
      List<Host> vhost = new ArrayList<>();  // approved host 
      hostRepo.findAll().forEach(i->{
        if(i.isOrganization()){
          vhost.add(i);
        }
        else{
          uhost.add(i);
        }
      });
      model.addAttribute("UHOST", uhost);
      model.addAttribute("VHOST", vhost);
        return "admin";
    }
    
    @GetMapping("/users/deleteAccount")
    public String deleteAccount(@RequestParam Long id, Model model){
      User user = userRepo.findById(id).get();
      userRepo.deleteById(user.getId());
      return "redirect:/admin";
    }
    
    @GetMapping("/host/deleteAccount")
    public String deleteHostAccount(@RequestParam Long id, Model model){
      Host host=hostRepo.findById(id).get();
      hostRepo.deleteById(host.getId());
      return "redirect:/admin";
    }

    @GetMapping("/users/approve")
    public String showCredentials(@RequestParam("id") Long id, Model model){
      Host unapprove = hostRepo.findById(id).get();
      if(unapprove.isOrganization()){
        unapprove.setOrganization(false);
      }
      else{
        unapprove.setOrganization(true);
      }
     
      hostRepo.save(unapprove);
      return "redirect:/admin";
    }
    

    






    @GetMapping("/updateEvent")
    public String updateEvent (){
      List<Event> events = new ArrayList<>();
      this.eventRepo.findAll().forEach(i->events.add(i));
      String date = new Date().toString();
      for(Event i: events){
        if (i.getEndingDate().compareTo(date) <0){
          OldEvent oldEvent= new OldEvent(
            i.getId(),
            i.getEventName(),
            i.getType1(),
            i.getType2(),
            i.getType3(),
            i.getType4(),
            i.getType5(),
            i.getEventDescription(),
            i.getEndingDate(),
            i.getLocation(),
            i.getImgDirectory(),
            i.getOrganizationId()
          );
          
          eventRepo.deleteById(i.getId());
          oldEventRepo.save(oldEvent);
        }
      }
      return "redirect:/admin";

    }

}
