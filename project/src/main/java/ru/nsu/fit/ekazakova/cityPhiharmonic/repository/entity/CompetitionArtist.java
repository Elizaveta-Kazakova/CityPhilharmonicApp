package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;

@Entity
@Data
@Table(name = "competition_artist")
public class CompetitionArtist {

    @EmbeddedId
    private CompetitionArtistKey id;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competition competition;

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artist artist;

    private Integer placeInRating;

}
