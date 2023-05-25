package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import jakarta.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static jakarta.persistence.TemporalType.DATE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDate {
    @Temporal(DATE)
    @DateTimeFormat(pattern="dd-MMM-YYYY")
    private Date startDate;
    @Temporal(DATE)
    @DateTimeFormat (pattern="dd-MMM-YYYY")
    private Date endDate;
}
