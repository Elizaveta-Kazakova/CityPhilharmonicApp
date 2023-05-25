package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import java.time.LocalDate;
import java.util.List;

public interface EventDetailsDto {
    Long getId();
    String getName();
    LocalDate getDate();
    String getOrganizer();
    String getCulturalBuilding();
    List<String> getArtists();
    List<String> getCompetitions();
}
