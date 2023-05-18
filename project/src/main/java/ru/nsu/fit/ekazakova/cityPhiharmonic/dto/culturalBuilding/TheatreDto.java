package ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheatreDto extends CulturalBuildingDto {
    private String theatreType;
}
