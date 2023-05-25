package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.GenreNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.GenreRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist.ArtistRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    @Transactional
    @Override
    public void createGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public List<GenreDto> list() {
        return genreRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public GenreDto findGenreById(Long id) throws GenreNotFoundException {
        return genreRepository.findById(id).map(this::toDto).orElseThrow(() -> new GenreNotFoundException("genre with id = " +
                id + " not found"));
    }

    @Override
    @Transactional
    public void updateGenre(Long id, GenreDto genreDto) throws GenreNotFoundException {
        Genre previousGenre = genreRepository.findById(id).orElseThrow(
                () -> new GenreNotFoundException("genre with id = " + id + " not found"));

        genreRepository.deleteAllById(previousGenre.getId());

        Genre genre = new Genre(previousGenre.getId(), genreDto.getName(), previousGenre.getArtists());
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public GenreDto findGenreByName(String name) throws GenreNotFoundException {
        Genre genre = genreRepository.findGenreByName(name);
        if (genre == null) {
            throw new GenreNotFoundException("genre with name = " + name + " not found");
        }
        return toDto(genre);
    }


}
