package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.organizer;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Organizer;

@Data
@AllArgsConstructor
public class OrganizerDetails {

    private Organizer organizer;
    private Integer numberOfEvents;
}
