package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist;

import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;

import java.util.List;

public interface ArtistCustomRepository {

    List<Artist> findArtistsByGenre(String genre);

    List<Artist> findArtistsByImpresario(String impresario);

}
