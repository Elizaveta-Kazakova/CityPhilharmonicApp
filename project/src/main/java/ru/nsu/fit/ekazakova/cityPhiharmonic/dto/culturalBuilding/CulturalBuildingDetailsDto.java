package ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CulturalBuildingDetailsDto {

    private CulturalBuildingDto culturalBuildingDto;
    private List<LocalDate> datesOfPlannedEvents;
}
