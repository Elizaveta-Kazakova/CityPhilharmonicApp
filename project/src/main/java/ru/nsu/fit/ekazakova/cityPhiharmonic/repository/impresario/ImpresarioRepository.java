package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.util.List;
import java.util.Optional;

public interface ImpresarioRepository extends CrudRepository<Impresario, Long> {

    //    @Query(value = "SELECT impresario FROM Impresario impresario where impresario.name=:name")
    Impresario findImpresarioByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT distinct i.id, i.name FROM public.impresario i " +
            "inner join public.artist_impresario ai on i.id = ai.impresario_id " +
            "inner join public.artist a on a.id = ai.artist_id where :artist = a.name")
    List<ImpresarioDetailsDto> findImpresariosByArtist(@Param("artist") String artist);

    @Query(nativeQuery = true, value = "select distinct i.id, i.name from public.impresario i " +
            "inner join artist_impresario ai on i.id = ai.impresario_id " +
            "inner join public.artist a on a.id = ai.artist_id " +
            "inner join public.artist_genre ag on a.id = ag.artist_id " +
            "inner join public.genre g on g.id = ag.genre_id " +
            "where :genre=g.name")
    List<ImpresarioDetailsDto> findImpresarioByGenre(@Param("genre") String genre);

    Optional<Impresario> findById(Long id);

    List<Impresario> findAll();

}
