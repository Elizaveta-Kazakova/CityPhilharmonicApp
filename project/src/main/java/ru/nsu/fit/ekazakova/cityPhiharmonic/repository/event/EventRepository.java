package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findById(Long id);

    List<Event> findAll();

    @Query(value = "SELECT e from Event e where e.culturalBuilding.name=:culturalBuilding")
    List<Event> findEventByCulturalBuilding(@Param("culturalBuilding") String culturalBuilding);

    @Query(value = "SELECT e " +
            "FROM Event e " +
            "WHERE e.date BETWEEN :startDate AND :endDate ")
    List<Event> findEventInPeriod(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query(value = "SELECT e from Event e where e.organizer.name=:organizer and" +
            " e.date BETWEEN :startDate AND :endDate ")
    List<Event> findEventInPeriodByOrganizer(@Param("startDate")LocalDate startDate,
                                                       @Param("endDate")LocalDate endDate, @Param("organizer")String organizer);

}


