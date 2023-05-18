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
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.OrganizerService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "organizer", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrganizer(@RequestBody OrganizerDto organizerDto) {
        organizerService.createOrganizer(organizerDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrganizer(@RequestBody OrganizerDto organizerDto, @PathVariable Long id) {
        organizerService.updateOrganizer(organizerDto, id);
        return ResponseEntity.ok().build();
    }

    // 11. Получить список организаторов культурных мероприятий и число
    //проведенных ими концертов в течение определенного периода времени.
    @GetMapping(value = "in-period", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrganizerDetailsDto>> getOrganizersInPeriod(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    @RequestParam LocalDate startDate,
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(organizerService.findOrganizersInPeriod(startDate, endDate));
    }
}
