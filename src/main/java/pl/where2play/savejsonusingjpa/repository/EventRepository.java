package pl.where2play.savejsonusingjpa.repository;


import org.springframework.data.repository.CrudRepository;
import pl.where2play.savejsonusingjpa.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
