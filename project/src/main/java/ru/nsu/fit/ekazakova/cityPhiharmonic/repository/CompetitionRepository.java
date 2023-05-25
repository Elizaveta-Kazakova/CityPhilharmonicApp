package ru.nsu.fit.ekazakova.cityPhiharmonic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Competition;

import java.util.List;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    @Query(value = "SELECT competitiom FROM Competition competitiom where competitiom.name=:name")
    Competition findCompetitionByName(@Param("name") String name);

    List<Competition> findAll();
}
