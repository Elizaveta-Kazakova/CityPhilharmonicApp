package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrganizerRepository extends CrudRepository<Organizer, Long> {

    @Query(value = "SELECT org FROM Organizer org where org.name=:name")
    Organizer findOrganizerByName(@Param("name")String name);

    @Query(nativeQuery = true, value = "SELECT o.name, count(e.id) " +
            "FROM public.organizer o INNER JOIN event e on o.id = e.organizer_id " +
            "WHERE e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY o.name")
    List<OrganizerDetailsDto> findOrganizersInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);

    List<Organizer> findAll();
}
