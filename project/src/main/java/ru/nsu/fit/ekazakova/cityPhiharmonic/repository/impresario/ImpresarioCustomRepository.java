package ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario;

import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;

import java.util.List;

public interface ImpresarioCustomRepository {

    List<Impresario> findImpresariosByArtist(String artist);

    List<Impresario> findImpresarioByGenre(String genre);
}
