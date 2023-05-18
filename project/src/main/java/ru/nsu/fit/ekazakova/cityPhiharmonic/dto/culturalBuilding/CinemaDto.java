package ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDto extends CulturalBuildingDto {
    private Integer screenSize;
}
