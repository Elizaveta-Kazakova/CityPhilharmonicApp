package ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CulturalBuildingInPeriodDto {
    private CulturalBuildingDto culturalBuildingDto;
    private LocalDate date;
}
