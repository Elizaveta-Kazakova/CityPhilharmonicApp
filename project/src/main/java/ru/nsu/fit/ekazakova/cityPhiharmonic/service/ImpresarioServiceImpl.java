package ru.nsu.fit.ekazakova.cityPhiharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ImpresarioNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Impresario;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.impresario.ImpresarioRepository;

import java.util.List;

@Service
public class ImpresarioServiceImpl implements ImpresarioService {
    private final ImpresarioRepository impresarioRepository;

    private ImpresarioDto toDto(Impresario impresario) {
        return new ImpresarioDto(impresario.getName());
    }

    @Autowired
    public ImpresarioServiceImpl(ImpresarioRepository impresarioRepository) {
        this.impresarioRepository = impresarioRepository;
    }

    @Transactional
    @Override
    public void createImpresario(ImpresarioDto impresarioDto) {
        Impresario impresario = new Impresario();
        impresario.setName(impresarioDto.getName());
        impresarioRepository.save(impresario);
    }

    @Transactional
    @Override
    public void updateImpresario(ImpresarioDto impresarioDto, Long id) {
        Impresario impresario = impresarioRepository.findById(id).orElseThrow(() ->
                new ImpresarioNotFoundException("impresario with id = " + id + " not found"));
        impresario.setName(impresarioDto.getName());

        impresarioRepository.save(impresario);
    }

    @Transactional
    @Override
    public List<ImpresarioDto> findImpresariosByArtist(String artist) {
        return impresarioRepository.findImpresariosByArtist(artist).stream().map(this::toDto).toList();
    }

    @Transactional
    @Override
    public List<ImpresarioDto> findImpresarioByGenre(String genre) {
        return impresarioRepository.findImpresarioByGenre(genre).stream().map(this::toDto).toList();
    }


}
