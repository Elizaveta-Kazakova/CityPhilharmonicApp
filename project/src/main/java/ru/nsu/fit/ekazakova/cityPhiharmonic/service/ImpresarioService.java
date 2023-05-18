package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;

import java.util.List;

public interface ImpresarioService {

    void createImpresario(ImpresarioDto impresarioDto);

    void updateImpresario(ImpresarioDto impresarioDto, Long id);

    List<ImpresarioDto> findImpresariosByArtist(String artist);

    List<ImpresarioDto> findImpresarioByGenre(String genre);
}
