package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building.CulturalBuilding;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Organizer organizer;
    @ManyToOne
    @JoinColumn(name = "cultural_building_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CulturalBuilding culturalBuilding;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST })
    @JoinTable(name = "event_artist",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
    private List<Artist> artists;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Competition> competitions;
}
