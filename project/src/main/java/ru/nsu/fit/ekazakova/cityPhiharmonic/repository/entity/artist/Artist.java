package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Data;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.CompetitionArtist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.util.List;

@Entity
@Data
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "artist_genre",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "artist_impresario",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "impresario_id", referencedColumnName = "id"))
    private List<Impresario> impresarios;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "artists")
    private List<Event> events;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<CompetitionArtist> ratings;

    @PreRemove
    public void preRemove() {
        this.getGenres().clear();
        this.genres = null;
    }

}
