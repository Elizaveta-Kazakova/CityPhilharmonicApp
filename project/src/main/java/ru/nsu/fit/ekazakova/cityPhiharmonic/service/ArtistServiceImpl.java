package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDetailsInCompetitionDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist.ArtistRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.GenreRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario.ImpresarioRepository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final ImpresarioRepository impresarioRepository;

    private ArtistDto toDto(Artist artist) {
        return new ArtistDto(artist.getId(), artist.getName(),
                artist.getImpresarios().stream().map(Impresario::getName).toList(),
                artist.getGenres().stream().map(Genre::getName).toList());
    }

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository,
                             GenreRepository genreRepository, ImpresarioRepository impresarioRepository) {
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.impresarioRepository = impresarioRepository;
    }

    @Override
    @Transactional
    public void createArtist(ArtistDto artistDto) {
        final Artist artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setGenres(artistDto.getGenres()
                .stream()
                .map(genreRepository::findGenreByName)
                .toList());
        artist.setImpresarios(artistDto.getImpresarios()
                .stream()
                .map(impresarioRepository::findImpresarioByName)
                .toList());
        artistRepository.save(artist);
    }

    @Override
    @Transactional
    public void updateArtist(ArtistDto artistDto, Long id) throws ArtistNotFoundException {
        Artist previousArtist = artistRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException("artist with id = " + id + " not found"));

        artistRepository.deleteAllById(artistDto.getId());

        Artist artist = new Artist();
        artist.setId(previousArtist.getId());
        artist.setName(artistDto.getName());
        artist.setGenres(artistDto.getGenres()
                .stream()
                .map(genreRepository::findGenreByName)
                .toList());
        artist.setImpresarios(artistDto.getImpresarios()
                .stream()
                .map(impresarioRepository::findImpresarioByName)
                .toList());

        artistRepository.save(artist);
    }

    @Override
    @Transactional
    public ArtistDto findArtistById(Long id) throws ArtistNotFoundException {
        return artistRepository.findById(id).map(this::toDto).orElseThrow(() ->
                new ArtistNotFoundException("artist with id = " + id + " not found"));
    }

    @Override
    @Transactional
    public ArtistDto findArtistByIName(String name) throws ArtistNotFoundException {
        Artist artist = artistRepository.findArtistByName(name);
        if (artist == null) {
            throw new ArtistNotFoundException("artist with name = " + name + " not found");
        }
        return toDto(artist);
    }

    @Override
    @Transactional
    public List<ArtistDto> list() {
        return artistRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public List<ArtistDetailsDto> findArtistsByGenre(String genre) {
        return artistRepository.findArtistsByGenre(genre);
    }

    @Override
    @Transactional
    public List<ArtistDetailsDto> findArtistsByImpresario(String impresario) {
        return artistRepository.findArtistsByImpresario(impresario);
    }

    @Override
    @Transactional
    public List<ArtistDetailsDto> findArtistsWithMultiplyGenres() {
        List<ArtistDetailsDto> artistDetailsDtos = artistRepository.findArtistsWithMultiplyGenres();
        return artistRepository.findArtistsWithMultiplyGenres();
    }

    @Override
    @Transactional
    public List<ArtistDetailsDto> findArtistsNotParticipatedInCompetitions(LocalDate startDate, LocalDate endDate) {
        return artistRepository.findArtistsNotParticipatedInCompetitions(startDate, endDate);
    }

    @Override
    public List<ArtistDetailsInCompetitionDto> findArtistsByCompetition(String competition) {
        return artistRepository.findArtistsByCompetition(competition);
    }


}
