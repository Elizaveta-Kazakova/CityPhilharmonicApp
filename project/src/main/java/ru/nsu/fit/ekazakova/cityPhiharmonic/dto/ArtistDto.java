package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    private Long id;
    private String name;
    private List<String> impresarios;
    private List<String> genres;
}
