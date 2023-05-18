package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerDetailsDto {

    private OrganizerDto organizerDto;
    private Integer numberOfEvents;
}
