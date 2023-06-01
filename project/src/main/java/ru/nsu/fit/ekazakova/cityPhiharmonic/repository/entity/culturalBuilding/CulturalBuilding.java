package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.culturalBuilding;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.Event;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cultural_building")
public class CulturalBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Integer numOfSeats;
    @OneToMany(mappedBy = "culturalBuilding", cascade = CascadeType.ALL)
    private List<Event> events;
}
