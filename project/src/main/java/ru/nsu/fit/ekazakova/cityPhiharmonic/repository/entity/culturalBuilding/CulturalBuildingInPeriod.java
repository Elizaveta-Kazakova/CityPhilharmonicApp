package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.culturalBuilding;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CulturalBuildingInPeriod {
    @Id
    private Long id;

    private String name;
    private Integer numOfSeats;
    private LocalDate date;


}
