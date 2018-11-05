package pl.where2play.savejsonusingjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.where2play.savejsonusingjpa.model.Event;
import pl.where2play.savejsonusingjpa.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public Iterable<Event> list() {
        return eventService.list();
    }

}
