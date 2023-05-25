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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ArtistService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.CulturalBuildingService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.EventService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.OrganizerService;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping(value = "event")
public class EventController {
    private final EventService eventService;
    private final OrganizerService organizerService;
    private final CulturalBuildingService culturalBuildingService;
    private final ArtistService artistService;

    @Autowired
    public EventController(EventService eventService, OrganizerService organizerService,
                           CulturalBuildingService culturalBuildingService, ArtistService artistService) {
        this.eventService = eventService;
        this.organizerService = organizerService;
        this.culturalBuildingService = culturalBuildingService;
        this.artistService = artistService;
    }

    @PostMapping(value = "/new")
    public String redirectToCreateEvent() {
        return "redirect:/event/new";
    }

    @PostMapping(params = "action=create")
    public String createEvent(@ModelAttribute("eventDto") EventDto eventDto) {
        eventService.createEvent(eventDto);
        return "redirect:/event";
    }

    @GetMapping("/update/{id}")
    public String getUpdateArtistForm(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("artistDto", artistService.findArtistById(id));
        } catch (ArtistNotFoundException ignored) {}
        return "artist/update";
    }

    @GetMapping("/{id}")
    public String getArtistById(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("artistDto", artistService.findArtistById(id));
        } catch (ArtistNotFoundException ignored) {}
        return "artist/show";
    }

    @GetMapping(value = "/search")
    public String searchArtist(@ModelAttribute("artistDto") ArtistDto artistDto) {
        return "artist/search";
    }


    @GetMapping("/new")
    public String getEventForm(@ModelAttribute("eventDto") EventDto eventDto, Model model) {
        model.addAttribute("organizersDto", organizerService.list().stream().map(OrganizerDto::getName));
        model.addAttribute("culturalBuildingsDto", culturalBuildingService.list().stream().map(CulturalBuildingDto::getName));
        model.addAttribute("artistsDto", artistService.list().stream().map(ArtistDto::getName));

        return "event/new";
    }

    @GetMapping()
    public String getEventList(Model model) {
        model.addAttribute("eventDto", eventService.list());
        return "event/index";
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

    @PostMapping(value = "by-cultural-building")
    public String redirectToEventByCulturalBuilding(@RequestParam String culturalBuilding) {
        return "redirect:/event/by-cultural-building/" + culturalBuilding;
    }

    @GetMapping(value = "by-cultural-building")
    public String getEventByCulturalBuildingForm(Model model) {
        model.addAttribute("culturalBuildingsDto", culturalBuildingService.list().stream().map(CulturalBuildingDto::getName).toList());
        return "event/by_cultural_building";
    }

    // 8. Получить перечень концертных мероприятий, проведенных в указанном
    //культурном сооружении.
    @GetMapping(value = "by-cultural-building/{culturalBuilding}")
    public String getEventByCulturalBuilding(@PathVariable String culturalBuilding, Model model) {
        model.addAttribute("culturalBuildingsDto", culturalBuildingService.list().stream().map(CulturalBuildingDto::getName).toList());
        model.addAttribute("eventsDto", eventService.findEventByCulturalBuilding(culturalBuilding));
        return "event/by_cultural_building";
    }
}
