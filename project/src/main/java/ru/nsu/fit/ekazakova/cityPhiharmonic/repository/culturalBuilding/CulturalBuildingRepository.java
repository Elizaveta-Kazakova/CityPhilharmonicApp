package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.culturalBuilding;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building.CulturalBuilding;

import java.util.List;

public interface CulturalBuildingRepository extends CrudRepository<CulturalBuilding, Long>,
        CulturalBuildingCustomRepository {

    @Query(value = "SELECT c FROM CulturalBuilding c where c.name=:name")
    CulturalBuilding findCulturalBuildingByName(@Param("name") String name);

    List<CulturalBuilding> findAll();
}
