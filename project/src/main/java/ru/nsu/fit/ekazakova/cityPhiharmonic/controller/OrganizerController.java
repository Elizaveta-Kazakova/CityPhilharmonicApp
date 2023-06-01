package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.OrganizerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public String redirectToOrganizersInPeriod(@ModelAttribute("startDate") String startDate,
                                               @ModelAttribute("endDate") String endDate) {
        return "redirect:/organizer/in-period/show?startDate=" + startDate + "&endDate=" + endDate;
    }

    @GetMapping(value = "in-period")
    public String getOrganizersInPeriodForm(@ModelAttribute("startDate") String startDate,
                                            @ModelAttribute("endDate") String endDate) {
        return "organizer/in_period";
    }

    // 11. Получить список организаторов культурных мероприятий и число
    //проведенных ими концертов в течение определенного периода времени.
    @GetMapping(value = "in-period/show")
    public String getOrganizersInPeriod(Model model, @RequestParam String startDate, @RequestParam String endDate) {

        DateTimeFormatter sqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startParsedDate = LocalDate.parse(startDate, sqlFormatter);
        LocalDate endParsedDate = LocalDate.parse(endDate, sqlFormatter);

        model.addAttribute("organizersInPeriod", organizerService.findOrganizersInPeriod(startParsedDate, endParsedDate));
        return "organizer/in_period";
    }
}
