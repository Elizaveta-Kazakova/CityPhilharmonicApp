package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;

import java.util.List;

public interface ImpresarioService {

    void createImpresario(ImpresarioDto impresarioDto);

    void updateImpresario(ImpresarioDto impresarioDto, Long id);

    List<ImpresarioDetailsDto> findImpresariosByArtist(String artist);

    List<ImpresarioDetailsDto> findImpresarioByGenre(String genre);

    ImpresarioDto findById(Long id);

    List<ImpresarioDto> list();

}
