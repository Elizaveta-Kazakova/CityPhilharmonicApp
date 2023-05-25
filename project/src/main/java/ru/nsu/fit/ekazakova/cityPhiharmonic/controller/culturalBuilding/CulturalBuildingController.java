package ru.nsu.fit.ekazakova.cityPhiharmonic.controller.culturalBuilding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.CulturalBuildingService;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<List<CulturalBuildingDto>> getCulturalBuildingByType(@RequestParam String buildingType) {
        return ResponseEntity.ok(culturalBuildingService.findCulturalBuildingByType(buildingType));
    }

    //1. Получить перечень культурных сооружений вмещающие не менее указанного числа зрителей
    @GetMapping(value = "by-size")
    public ResponseEntity<List<CulturalBuildingDto>> getCulturalBuildingByNumOfSets(@RequestParam Integer numOfSeats) {
        return ResponseEntity.ok(culturalBuildingService.findCulturalBuildingByNumOfSeats(numOfSeats));
    }

    // 12. Получить перечень культурных сооружений, а также даты проведения на
    //них культурных мероприятий в течение определенного периода времени.
    public ResponseEntity<List<CulturalBuildingDetailsDto>> getCulturalBuildingsInPeriod(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(culturalBuildingService.findCulturalBuildingsInPeriod(startDate, endDate));
    }


}
