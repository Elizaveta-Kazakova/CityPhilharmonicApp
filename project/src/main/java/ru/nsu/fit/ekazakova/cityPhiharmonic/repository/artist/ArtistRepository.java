package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist;

import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> /*, ArtistCustomRepository*/ {

    @Query(nativeQuery = true, value = "SELECT a.id, a.name from public.artist a inner join " +
            "(select ag.artist_id, count(genre_id) g_count from public.artist_genre ag " +
            "group by ag.artist_id) as aigc " +
            "on aigc.artist_id = a.id " +
            "where g_count > 1")
    List<Artist> findArtistsWithMultiplyGenres();

    @Query(value = "SELECT a FROM Artist a" /*HAVING COUNT(a.ratings) = 0 "*/)
    List<Artist> findArtistsNotParticipatedInCompetitions();

    @Query(value = "SELECT a FROM Artist a where a.name=:name")
    Artist findArtistByName(@Param("name") String name);

    List<Artist> findAll();

    @Query(nativeQuery = true, value = "SELECT a.id, a.name FROM public.artist a" +
            " inner join public.artist_genre ag on a.id = ag.artist_id inner join public.genre g " +
            "on g.id = ag.genre_id where :genre = g.name")
    List<ArtistDetailsDto> findArtistsByGenre(@Param("genre") String genre);

    @Query(nativeQuery = true, value = "SELECT a.id, a.name FROM public.artist a" +
            " inner join public.artist_impresario ai on a.id = ai.artist_id " +
            "inner join public.impresario i on i.id = ai.impresario_id where :impresario = i.name")
    List<ArtistDetailsDto> findArtistsByImpresario(@Param("impresario") String impresario);

    @Query(nativeQuery = true, value = "SELECT a.id, a.name from public.artist a " +
            "inner join competition_artist ca on a.id = ca.artist_id " +
            "inner join competition c on c.id = ca.competition_id " +
            "where c.name = :competition and ca.place_in_rating <= 3")
    List<ArtistDetailsDto> findArtistsByCompetition(@Param("competition") String competition);

    void deleteAllById(Long id);
}
