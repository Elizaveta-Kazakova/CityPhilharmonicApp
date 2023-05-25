package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDate;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.OrganizerService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping(value = "organizer")
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

    @PostMapping(value = "in-period")
    public String redirectToOrganizersInPeriod(Model model, @ModelAttribute("startDate") String startDate,
                                               @ModelAttribute("endDate") String endDate) {
        String pstartDate = (String) model.getAttribute("startDate");
        String pendDate = (String) model.getAttribute("endDate");

        System.out.println("startDate=" + pstartDate + " endDate=" + pendDate);
        return "redirect:/organizer/in-period/" + startDate + "/" + endDate;
    }

    @GetMapping(value = "in-period")
    public String getOrganizersInPeriodForm(@ModelAttribute("startDate") String startDate,
                                            @ModelAttribute("endDate") String eventDate) {
        return "organizer/in_period";
    }

    // 11. Получить список организаторов культурных мероприятий и число
    //проведенных ими концертов в течение определенного периода времени.
    @GetMapping(value = "in-period/{startDate}/{endDate}")
    public String getOrganizersInPeriod(Model model, @PathVariable String startDate, @PathVariable String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("startDate=" + startDate + " endDate=" + endDate);

        LocalDate startParsedDate = LocalDate.parse(startDate, formatter);
        System.out.println("dsjfldnsfla");
        LocalDate endParsedDate = LocalDate.parse(endDate, formatter);
        System.out.println("startDate=" + startParsedDate + " endDate=" + endParsedDate);


        DateTimeFormatter sqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startParsedDate.format(sqlFormatter);
        String end = endParsedDate.format(sqlFormatter);

        System.out.println("startDate=" + start + " endDate=" + end);

        startParsedDate = LocalDate.parse(start, sqlFormatter);
        endParsedDate = LocalDate.parse(end, sqlFormatter);

        System.out.println("startDate=" + startParsedDate + " endDate=" + endParsedDate);

        model.addAttribute("organizersByArtist", organizerService.findOrganizersInPeriod(startParsedDate, endParsedDate));
        return "organizer/in_period";
    }
}
