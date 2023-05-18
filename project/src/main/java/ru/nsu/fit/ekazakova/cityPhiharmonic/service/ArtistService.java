package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;

import java.util.List;

public interface ArtistService {

    void updateArtist(ArtistDto artistDto, Long id) throws ArtistNotFoundException;

    void createArtist(ArtistDto artistDto);

    ArtistDto findArtistById(Long id) throws ArtistNotFoundException;

    ArtistDto findArtistByIName(String name) throws ArtistNotFoundException;

    List<ArtistDto> list();

    List<ArtistDto> findArtistsByGenre(String genre);

    List<ArtistDto> findArtistsByImpresario(String impresario);

    List<ArtistDto> findArtistsWithMultiplyGenres();

    List<ArtistDto> findArtistsNotParticipatedInCompetitions();
}
