package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.OrganizerDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrganizerService {

    void createOrganizer(OrganizerDto organizerDto);

    void updateOrganizer(OrganizerDto organizerDto, Long id);

    List<OrganizerDetailsDto> findOrganizersInPeriod(LocalDate startDate, LocalDate endDate);

    List<OrganizerDto> list();

}
