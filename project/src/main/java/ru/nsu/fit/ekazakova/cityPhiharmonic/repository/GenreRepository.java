package ru.nsu.fit.ekazakova.cityPhiharmonic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    @Query(value = "SELECT genre FROM Genre genre where genre.name=:name")
    Genre  findGenreByName(@Param("name") String name);

    List<Genre> findAll();

    Optional<Genre> findById(Long id);

    void deleteAllById(Long id);

    @Query(nativeQuery = true, value = "SELECT g.name from public.genre g " +
            "inner join artist_genre ag on g.id = ag.genre_id " +
            "inner join artist a on a.id = ag.artist_id " +
            "where a.name=:artist")
    List<GenreDetailsDto> findGenreByArtist(@Param("artist") String artistName);


}
