package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.GenreNotFoundException;

import java.util.List;

public interface GenreService {

    void createGenre(GenreDto genreDto);

    List<GenreDto> list();

    GenreDto findGenreById(Long Id) throws GenreNotFoundException;

    void updateGenre(Long id, GenreDto genreDto) throws GenreNotFoundException;

    GenreDto findGenreByName(String name) throws GenreNotFoundException;

    List<GenreDetailsDto> findGenreByArtist(String artistName);
}
