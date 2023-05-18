package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.artist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long>, ArtistCustomRepository {

    @Query(value = "SELECT a FROM Artist a" /*HAVING COUNT(a.genres) > 1"*/)
    List<Artist> findArtistsWithMultiplyGenres();

    @Query(value = "SELECT a FROM Artist a" /*HAVING COUNT(a.ratings) = 0 "*/)
    List<Artist> findArtistsNotParticipatedInCompetitions();

    @Query(value = "SELECT a FROM Artist a where a.name=:name")
    Artist findArtistByName(@Param("name") String name);

    void deleteAllById(Long id);
}
