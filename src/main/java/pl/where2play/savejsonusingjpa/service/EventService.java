package pl.where2play.savejsonusingjpa.service;

import org.springframework.stereotype.Service;
import pl.where2play.savejsonusingjpa.model.Event;
import pl.where2play.savejsonusingjpa.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Iterable<Event> save(List<Event> events) {
        return eventRepository.saveAll(events);
    }

    public Iterable<Event> list() {
        return eventRepository.findAll();
    }
}
