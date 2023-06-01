package ru.nsu.fit.ekazakova.cityPhiharmonic.controller.culturalBuilding;

import org.springframework.beans.factory.annotation.Autowired;
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
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.CulturalBuildingService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping(value = "cultural_building")
public class CulturalBuildingController {
    private final CulturalBuildingService culturalBuildingService;

    @Autowired
    public CulturalBuildingController(CulturalBuildingService culturalBuildingService) {
        this.culturalBuildingService = culturalBuildingService;
    }

    @PostMapping(value = "new")
    public ResponseEntity<Void> createCulturalBuilding(@RequestBody CulturalBuildingDto culturalBuildingDto) {
        culturalBuildingService.createCulturalBuilding(culturalBuildingDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Void> updateCulturalBuilding(@RequestBody CulturalBuildingDto culturalBuildingDto,
                                                       @PathVariable Long id) {
        culturalBuildingService.updateCulturalBuilding(culturalBuildingDto, id);
        return ResponseEntity.ok().build();
    }

    // 1. Получить культурное сооружение по id
    @GetMapping(value = "{id}")
    public ResponseEntity<CulturalBuildingDto> getCulturalBuilding(@PathVariable Long id) {
        return ResponseEntity.ok(culturalBuildingService.findCulturalBuildingById(id));
    }

    // 1. Получить перечень культурных сооружений указанного типа
    @GetMapping(value = "by-type")
    public String getCulturalBuildingByType(@RequestParam String buildingType) {
        return "";
    }

    //1. Получить перечень культурных сооружений вмещающие не менее указанного числа зрителей
    @GetMapping(value = "by-size")
    public String getCulturalBuildingByNumOfSeatsForm() {
        return "cultural_building/by_size";
    }

    @PostMapping(value = "by-size")
    public String redirectToCulturalBuildingByNumOfSeats(@RequestParam Integer numOfSeats) {
        return "redirect:/cultural_building/by-size/show/" + numOfSeats;
    }

    @GetMapping(value = "by-size/show/{numOfSeats}")
    public String getCulturalBuildingByNumOfSeats(Model model, @PathVariable Integer numOfSeats) {
        model.addAttribute("culturalBuildingsDto",
                culturalBuildingService.findCulturalBuildingByNumOfSeats(numOfSeats));
        return "cultural_building/by_size";
    }

    // 12. Получить перечень культурных сооружений, а также даты проведения на
    //них культурных мероприятий в течение определенного периода времени.
    @GetMapping(value = "in-period")
    public String getCulturalBuildingsInPeriodForm(
            @ModelAttribute("startDate") String startDate,
            @ModelAttribute("endDate") String endDate) {
        return "cultural_building/in_period";
    }

    @PostMapping(value = "in-period")
    public String redirectToCulturalBuildingsInPeriod(
            @ModelAttribute("startDate") String startDate,
            @ModelAttribute("endDate") String endDate) {
        return "redirect:/cultural_building/in-period/show?startDate=" + startDate + "&endDate=" + endDate;
    }


    @GetMapping(value = "in-period/show")
    public String getCulturalBuildingsInPeriod(Model model,
                                               @RequestParam String startDate, @RequestParam String endDate) {

        DateTimeFormatter sqlFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startParsedDate = LocalDate.parse(startDate, sqlFormatter);
        LocalDate endParsedDate = LocalDate.parse(endDate, sqlFormatter);

        model.addAttribute("culturalBuildingsDto", culturalBuildingService.findCulturalBuildingsInPeriod(
                startParsedDate, endParsedDate
        ));

        return "cultural_building/in_period";
    }


}
