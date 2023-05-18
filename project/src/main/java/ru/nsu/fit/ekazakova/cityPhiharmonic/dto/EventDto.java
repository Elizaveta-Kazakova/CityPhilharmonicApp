package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String name;
    private LocalDate date;
    private String organizer;
    private String culturalBuilding;
    private List<String> artists;
}
