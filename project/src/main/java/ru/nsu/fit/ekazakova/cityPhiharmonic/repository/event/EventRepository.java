package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.event;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Long>, EventCustomRepository {

    Optional<Event> findById(Long id);
}


