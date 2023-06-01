package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.culturalBuilding.CulturalBuildingDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.culturalBuilding.CulturalBuilding;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.culturalBuilding.CulturalBuildingInPeriod;

import java.time.LocalDate;
import java.util.List;

public interface CulturalBuildingRepository extends CrudRepository<CulturalBuilding, Long> {

    @Query(value = "SELECT c FROM CulturalBuilding c where c.name=:name")
    CulturalBuilding findCulturalBuildingByName(@Param("name") String name);

    List<CulturalBuilding> findAll();

    @Query(value = "select cb from CulturalBuilding cb where cb.numOfSeats >= :numOfSeats")
    List<CulturalBuilding> findCulturalBuildingByNumOfSeats(@Param("numOfSeats") Integer numOfSeats);

//    List<CulturalBuilding> findCulturalBuildingByType(String tableName);

    @Query(value = "SELECT  distinct  new CulturalBuildingInPeriod(cb.id, cb.name, cb.numOfSeats, e.date) from CulturalBuilding cb inner join Event e " +
            "where e.date between :startDate and :endDate")
    List<CulturalBuildingInPeriod> findCulturalBuildingsInPeriod(LocalDate startDate, LocalDate endDate);
}
