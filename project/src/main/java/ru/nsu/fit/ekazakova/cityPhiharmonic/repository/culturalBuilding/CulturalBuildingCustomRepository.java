package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding;

import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building.CulturalBuilding;

import java.time.LocalDate;
import java.util.List;

public interface CulturalBuildingCustomRepository {

    List<CulturalBuilding> findCulturalBuildingByType(String tableName);

    List<CulturalBuilding> findCulturalBuildingByNumOfSeats(Integer numOfSeats);

    List<CulturalBuildingDetails> findCulturalBuildingsInPeriod(LocalDate startDate, LocalDate endDate);

}
