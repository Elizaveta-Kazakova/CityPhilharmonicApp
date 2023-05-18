package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CompetitionArtistKey implements Serializable {

    private Long competitionId;
    private Long artistId;
}
