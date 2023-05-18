package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDto;

import java.time.LocalDate;
import java.util.List;

public interface CulturalBuildingService {

    void createCulturalBuilding(CulturalBuildingDto culturalBuildingDto);

    void updateCulturalBuilding(CulturalBuildingDto culturalBuildingDto, Long id);

    CulturalBuildingDto findCulturalBuildingById(Long id);

    List<CulturalBuildingDto> findCulturalBuildingByType(String type);

    List<CulturalBuildingDto> findCulturalBuildingByNumOfSeats(Integer numOfSeats);

    List<CulturalBuildingDetailsDto> findCulturalBuildingsInPeriod(LocalDate startDate, LocalDate endDate);
}
