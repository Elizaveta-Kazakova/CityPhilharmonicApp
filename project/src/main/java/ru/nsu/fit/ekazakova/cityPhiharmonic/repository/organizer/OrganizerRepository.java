package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;

public interface OrganizerRepository extends CrudRepository<Organizer, Long>, OrganizerCustomRepository {

    @Query(value = "SELECT org FROM Organizer org where org.name=:name")
    Organizer findOrganizerByName(@Param("name")String name);
}
