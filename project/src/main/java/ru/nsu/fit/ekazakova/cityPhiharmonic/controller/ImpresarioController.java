package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ImpresarioService;

import java.util.List;

@RestController
@RequestMapping(value = "impresario", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImpresarioController {
    private final ImpresarioService impresarioService;

    @Autowired
    public ImpresarioController(ImpresarioService impresarioService) {
        this.impresarioService = impresarioService;
    }

    @PostMapping(value = "new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createImpresario(@RequestBody ImpresarioDto impresarioDto) {
        impresarioService.createImpresario(impresarioDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateImpresario(@RequestBody ImpresarioDto impresarioDto, @PathVariable Long id) {
        impresarioService.updateImpresario(impresarioDto, id);
        return ResponseEntity.ok().build();
    }

//    // 5. Получить список импресарио указанного артиста.
//    @GetMapping(value = "by-artist", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ImpresarioDto>> getImpresariosByArtist(@RequestParam String artist) {
//        return ResponseEntity.ok(impresarioService.findImpresariosByArtist(artist));
//    }
//
//    // 9. Получить список импресарио определенного жанра.
//    @GetMapping(value = "by-genre", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ImpresarioDto>> getImpresarioByGenre(@RequestParam String genre) {
//        return ResponseEntity.ok(impresarioService.findImpresarioByGenre(genre));
//    }
}
