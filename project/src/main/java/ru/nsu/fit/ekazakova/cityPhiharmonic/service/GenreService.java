package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;

import java.util.List;

public interface GenreService {

    void createGenre(GenreDto genreDto);

    List<GenreDto> list();

    GenreDto findGenreById(Long Id);

    void updateGenre(Long id, GenreDto genreDto);
}
