package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ImpresarioNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ArtistService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.GenreService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ImpresarioService;

import java.util.List;

@Controller
@RequestMapping(value = "impresario")
public class ImpresarioController {
    private final ImpresarioService impresarioService;
    private final ArtistService artistService;
    private final GenreService genreService;

    @Autowired
    public ImpresarioController(ImpresarioService impresarioService, ArtistService artistService,
                                GenreService genreService) {
        this.impresarioService = impresarioService;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @PostMapping(value = "new")
    public ResponseEntity<Void> createImpresario(@RequestBody ImpresarioDto impresarioDto) {
        impresarioService.createImpresario(impresarioDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Void> updateImpresario(@RequestBody ImpresarioDto impresarioDto, @PathVariable Long id) {
        impresarioService.updateImpresario(impresarioDto, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "by-artist")
    public String redirectToImpresariosByArtist(@RequestParam String artist) {
        return "redirect:/impresario/by-artist/" + artist;
    }

    @PostMapping(value = "by-genre")
    public String redirectToImpresariosByGenre(@RequestParam String genre) {
        return "redirect:/impresario/by-genre/" + genre;
    }

    @GetMapping("/{id}")
    public String getArtistById(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("impresarioDto", impresarioService.findById(id));
        } catch (ImpresarioNotFoundException ignored) {}
        return "impresario/show";
    }

    @GetMapping(value = "by-artist")
    public String getImpresariosByArtistForm(Model model) {
        model.addAttribute("artistsDto", artistService.list().stream().map(ArtistDto::getName).toList());
        return "impresario/by_artist";
    }

    // 5. Получить список импресарио указанного артиста.
    @GetMapping(value = "by-artist/{artist}")
    public String getImpresariosByArtist(@PathVariable String artist, Model model) {
        model.addAttribute("artistsDto", artistService.list().stream().map(ArtistDto::getName).toList());
        model.addAttribute("impresariosByArtist", impresarioService.
                findImpresariosByArtist(artist));
        return "impresario/by_artist";
    }

    @GetMapping(value = "by-genre")
    public String getImpresariosByGenreForm(Model model) {
        model.addAttribute("genreDto", genreService.list().stream().map(GenreDto::getName).toList());
        return "impresario/by_artist";
    }

    // 9. Получить список импресарио определенного жанра.
    @GetMapping(value = "by-genre/{genre}")
    public ResponseEntity<List<ImpresarioDetailsDto>> getImpresarioByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(impresarioService.findImpresarioByGenre(genre));
    }
}
