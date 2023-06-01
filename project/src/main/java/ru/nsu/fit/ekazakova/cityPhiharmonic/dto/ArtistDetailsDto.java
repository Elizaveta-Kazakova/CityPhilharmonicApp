package ru.nsu.fit.ekazakova.cityPhiharmonic.dto;

import java.util.List;

public interface ArtistDetailsDto {
    Long getId();
    String getName();
    List<String> getGenres();
}
