package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building.CulturalBuilding;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CulturalBuildingDetails {

    private CulturalBuilding culturalBuilding;
    private List<LocalDate> datesOfPlannedEvents;

}
