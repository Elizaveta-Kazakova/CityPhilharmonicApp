package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDetailsInCompetitionDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;

import java.time.LocalDate;
import java.util.List;

public interface ArtistService {

    void updateArtist(ArtistDto artistDto, Long id) throws ArtistNotFoundException;

    void createArtist(ArtistDto artistDto);

    ArtistDto findArtistById(Long id) throws ArtistNotFoundException;

    ArtistDto findArtistByIName(String name) throws ArtistNotFoundException;

    List<ArtistDto> list();

    List<ArtistDetailsDto> findArtistsByGenre(String genre);

    List<ArtistDetailsDto> findArtistsByImpresario(String impresario);

    List<ArtistDetailsDto> findArtistsWithMultiplyGenres();

    List<ArtistDetailsDto> findArtistsNotParticipatedInCompetitions(LocalDate startDate, LocalDate endDate);

    List<ArtistDetailsInCompetitionDto> findArtistsByCompetition(String competition);
}
