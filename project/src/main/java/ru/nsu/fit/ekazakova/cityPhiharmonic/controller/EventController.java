package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.EventService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEvent(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id) {
        eventService.updateEvent(eventDto, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findEventById(id));
    }

    // 6. Получить перечень концертных мероприятий, проведенных в течение
    //заданного периода времени в целом
    @GetMapping(value = "in-period", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getEventInPeriod(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam LocalDate startDate,
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(eventService.findEventInPeriod(startDate, endDate));
    }

    // 6. Получить перечень концертных мероприятий, проведенных в течение
    //заданного периода времени c указанным организатором.
    @GetMapping(value = "in-period/by-organizer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getEventInPeriodByOrganizer(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate endDate,
            @RequestParam String organizer) {
        return ResponseEntity.ok(eventService.findEventInPeriodByOrganizer(startDate, endDate, organizer));
    }

    // 8. Получить перечень концертных мероприятий, проведенных в указанном
    //культурном сооружении.
    @GetMapping(value = "by-cultural-building", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getEventByCulturalBuilding(@RequestParam String culturalBuilding) {
        return ResponseEntity.ok(eventService.findEventByCulturalBuilding(culturalBuilding));
    }
}
