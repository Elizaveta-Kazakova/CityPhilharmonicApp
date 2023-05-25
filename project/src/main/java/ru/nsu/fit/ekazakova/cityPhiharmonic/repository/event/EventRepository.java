package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Long>, EventCustomRepository {

    Optional<Event> findById(Long id);

    List<Event> findAll();

    @Query(nativeQuery = true, value = "SELECT e.id, e.name from public.event e " +
            "inner join cultural_building cb on cb.id = e.cultural_building_id " +
            "where cb.name = :culturalBuilding")
    List<EventDetailsDto> findEventByCulturalBuilding(@Param("culturalBuilding") String culturalBuilding);
}


