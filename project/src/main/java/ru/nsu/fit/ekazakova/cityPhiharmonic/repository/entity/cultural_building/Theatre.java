package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.cultural_building;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "theatre")
public class Theatre extends CulturalBuilding {
    private String theatreType;

}
