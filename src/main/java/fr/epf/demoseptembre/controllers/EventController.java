package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.Event;
import fr.epf.demoseptembre.persistence.EventDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private final EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }



    @GetMapping("/event")
    public String addEventPage (Model model) {
        Event evnt = new Event(null,null,null,null);
        model.addAttribute(evnt);
        return "event";
    }

    @PostMapping("event")
    public String addEvent (Model model, Event evnt){
        eventDao.save(evnt);
        return "redirect:/";
    }

    @GetMapping("/calendar")
    public String openCalendar (Model model) {
        model.addAttribute("data2", eventDao.findAll());
        return "event";
    }

}