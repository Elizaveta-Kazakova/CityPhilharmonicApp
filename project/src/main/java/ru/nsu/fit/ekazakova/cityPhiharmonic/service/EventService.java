package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.EventDto;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    void createEvent(EventDto eventDto);

    void updateEvent(EventDto eventDto, Long id);

    EventDto findEventById(Long id);

    List<EventDto> findEventInPeriod(LocalDate startDate, LocalDate endDate);

    List<EventDto> findEventInPeriodByOrganizer(LocalDate startDate, LocalDate endDate,
                                                String organizer);

    List<EventDto> findEventByCulturalBuilding(String culturalBuilding);

}
