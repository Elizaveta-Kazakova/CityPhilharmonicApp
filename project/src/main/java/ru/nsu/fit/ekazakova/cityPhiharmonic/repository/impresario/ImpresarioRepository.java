package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.util.List;

public interface ImpresarioRepository extends CrudRepository<Impresario, Long>, ImpresarioCustomRepository {

    //    @Query(value = "SELECT impresario FROM Impresario impresario where impresario.name=:name")
    Impresario findImpresarioByName(@Param("name") String name);

    List<Impresario> findAll();

}
