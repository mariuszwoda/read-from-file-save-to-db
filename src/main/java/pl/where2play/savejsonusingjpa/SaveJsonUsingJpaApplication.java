package pl.where2play.savejsonusingjpa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.where2play.savejsonusingjpa.model.Event;
import pl.where2play.savejsonusingjpa.model.EventDto;
import pl.where2play.savejsonusingjpa.service.EventService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

@SpringBootApplication
public class SaveJsonUsingJpaApplication {

    private static final Logger log = java.util.logging.Logger.getLogger(SaveJsonUsingJpaApplication.class.getName());
    private ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(SaveJsonUsingJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(EventService eventService) {

        return args -> {

            if (args.length == 0) {
                log.severe("File name missing as a program arg; press ctrL+c and run again...");
            } else {
                String fileName = args[0];
                log.info("File have found: " + fileName);

                try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

                    EventDto eventDtoPrevious = new EventDto();

                    stream
                            .filter(line -> line.contains("id"))
                            .sorted()
                            .forEach(
                                    line -> {
                                        TypeReference<EventDto> typeRef = new TypeReference<EventDto>() {
                                        };

                                        log.info("Event 'previous': " + eventDtoPrevious.toString());
                                        EventDto eventDto = null;
                                        try {
                                            eventDto = mapper.readValue(line, typeRef);
                                            log.info("Event current: " + eventDto.toString());

                                            if (eventDto.getId().equalsIgnoreCase(eventDtoPrevious.getId())) {

                                                Event event = new Event();

                                                long duration = eventDto.getTimestamp() - eventDtoPrevious.getTimestamp();
                                                if (duration > 4 || duration < -4)
                                                    event.setAlert(true);
                                                event.setDuration(Math.abs(duration));
                                                event.setId(eventDto.getId());
                                                if (eventDto.getHost() != null) {
                                                    event.setHost(eventDto.getHost());
                                                    event.setType(eventDto.getType());
                                                }
                                                eventService.save(event);
                                                log.info("Event saved properly");
                                            }
                                        } catch (IOException e) {
                                            log.severe("Unable to save event: " + e.getMessage());
                                        }

                                        eventDtoPrevious.setId(eventDto.getId());
                                        eventDtoPrevious.setTimestamp(eventDto.getTimestamp());
                                    }
                            );
                }
            }
            log.info("To check results open your fav browser and go to location: http://localhost:8080/events/list");
        };
    }
}
