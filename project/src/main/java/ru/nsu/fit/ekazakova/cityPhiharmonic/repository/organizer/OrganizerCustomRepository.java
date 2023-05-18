package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer;

import java.time.LocalDate;
import java.util.List;

public interface OrganizerCustomRepository {

    List<OrganizerDetails> findOrganizersInPeriod(LocalDate startDate, LocalDate endDate);
}
