package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

public interface ImpresarioRepository extends CrudRepository<Impresario, Long>, ImpresarioCustomRepository {

//    @Query(value = "SELECT impresario FROM Impresario impresario where impresario.name=:name")
    Impresario findImpresarioByName(@Param("name") String name);

}
