package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;

import java.util.List;

public interface ArtistService {

    void updateArtist(ArtistDto artistDto, Long id);

    void createArtist(ArtistDto artistDto);

    ArtistDto findArtistById(Long id);

    List<ArtistDto> findArtistsByGenre(String genre);

    List<ArtistDto> findArtistsByImpresario(String impresario);

    List<ArtistDto> findArtistsWithMultiplyGenres();

    List<ArtistDto> findArtistsNotParticipatedInCompetitions();
}
