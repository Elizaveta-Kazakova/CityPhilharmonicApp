package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.event;

import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventCustomRepository {

    List<Event> findEventInPeriod(LocalDate startDate, LocalDate endDate);

    List<Event> findEventInPeriodByOrganizer(LocalDate startDate, LocalDate endDate, String organizer);

}
