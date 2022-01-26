package com.group.event_notifier;

import java.util.ArrayList;
import java.util.List;

import com.group.event_notifier.security.Host;
import com.group.event_notifier.security.HostRepository;
import com.group.event_notifier.security.User;
import com.group.event_notifier.security.UserRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@Data
@RequestMapping("/mainPage")
@Controller
public class MainController  {
    private final UserRepository userRepo;
    private final EventRepository eventRepo;
    private final HostRepository hostRepo;

    @GetMapping("/featured")
    public String fetchFeatured(Model model) {
        List<Event> featured = new ArrayList<>();
        eventRepo.findAll().forEach(i -> {
            Host host = hostRepo.findById(i.getOrganizationId()).get();
            if (host.isOrganization()) {
                featured.add(i);
            }
        });
        model.addAttribute("EVENT", featured);

        return "event_list";
    }
    @GetMapping("/search")
    public String searcher (Model model ,@RequestParam("search") String keyword){
        List<Event> events =new ArrayList<>();
        
        eventRepo.search(keyword).forEach(i->events.add(i));
        
        
        model.addAttribute("EVENT",events);
        return "search";

    }

    @GetMapping
    public String allEvent(Model model) {
        List<Event> events = new ArrayList<>();
        this.eventRepo.findAll().forEach(i -> events.add(i));
        model.addAttribute("EVENT", events);
        return "event_list";
    }

    @GetMapping("/forYou")
    public String fetchForYou( Model model,@AuthenticationPrincipal User user) {
        List<Event> forYou = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        this.eventRepo.findAll().forEach(i -> events.add(i));
        // User user = userRepo.findById(id).get();
        List<String> interest = new ArrayList<>();
        interest.add(user.getChoice5());
        interest.add(user.getChoice4());
        interest.add(user.getChoice3());
        interest.add(user.getChoice2());
        interest.add(user.getChoice1());
        for (Event i : events) {
            List<String> type = new ArrayList<>();
            type.add(i.getType1());
            type.add(i.getType2());
            type.add(i.getType3());
            type.add(i.getType4());
            type.add(i.getType5());
            int totalValue = 0;
            for (String j : type) {
                if (j != null && interest.contains(j)) {
                    totalValue += (interest.indexOf(j) + 1);
                }
            }
            if (totalValue > 2) {
                forYou.add(i);
            }

        }

        model.addAttribute("EVENT", forYou);
        model.addAttribute("user",user);

        return "event_list";

    }

    // @GetMapping("catagories/{value}")
    // public String fetchCatagory(@PathVariable("value") String value, Model model) {
    //     List<String> type = new ArrayList<>();
    //     List<Event> events = new ArrayList<>();
    //     this.eventRepo.findAll().forEach(i -> {
    //         type.add(i.getType1());
    //         type.add(i.getType2());
    //         type.add(i.getType3());
    //         type.add(i.getType4());
    //         type.add(i.getType5());
    //         if (type.contains(value)) {
    //             events.add(i);
    //         }
    //         type.clear();

    //     });
    //     return "event";
    // }

}
