package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.GenreNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ArtistService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.GenreService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ImpresarioService;

import java.util.List;

@Controller
@RequestMapping(value = "artist")
public class ArtistController {
    private final ArtistService artistService;
    private final ImpresarioService impresarioService;
    private final GenreService genreService;

    @Autowired
    public ArtistController(ArtistService artistService, ImpresarioService impresarioService,
                            GenreService genreService) {
        this.artistService = artistService;
        this.impresarioService = impresarioService;
        this.genreService = genreService;
    }

    @PostMapping(value = "/new")
    public String redirectToCreateArtist() {
        return "redirect:/artist/new";
    }

    @PostMapping(value = "/{id}", params = "action=update")
    public String redirectToUpdateArtist() {
        return "redirect:/artist/update/{id}";
    }

    @PostMapping(value = "/{id}", params = "action=back")
    public String redirectToArtistList() {
        return "redirect:/artist";
    }

    @PostMapping(params = "action=search")
    public String redirectToSearchArtist() {
        return "redirect:/artist/search";
    }

    @PostMapping(params = "action=create")
    public String createArtist(@ModelAttribute("artistDto") ArtistDto artistDto) {
        artistService.createArtist(artistDto);
        return "redirect:/artist";
    }

    @PostMapping(value = "/update/{id}")
    public String updateArtist(@ModelAttribute("artistDto") ArtistDto artistDto, @PathVariable Long id) {
        try {
            artistService.updateArtist(artistDto, id);
        } catch (ArtistNotFoundException ignored) {}
        return "redirect:/artist";
    }

    @PostMapping(value = "/search")
    public String getSearchedArtist(@ModelAttribute("artistDto") ArtistDto artistDto, Model model) {
        try {
            return getArtistById(artistService.findArtistByIName(artistDto.getName()).getId(), model);
        } catch (ArtistNotFoundException e) {
            return "artist/searchWithError";
        }
    }

    @GetMapping("/update/{id}")
    public String getUpdatArtistForm(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("artistDto", artistService.findArtistById(id));
        } catch (ArtistNotFoundException ignored) {}
        return "artist/update";
    }

    @GetMapping("/{id}")
    public String getArtistById(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("artistDto", artistService.findArtistById(id));
        } catch (ArtistNotFoundException ignored) {}
        return "artist/show";
    }

    @GetMapping(value = "/search")
    public String searchArtist(@ModelAttribute("artistDto") ArtistDto artistDto) {
        return "artist/search";
    }


    @GetMapping("/new")
    public String getArtistForm(@ModelAttribute("artistDto") ArtistDto artistDto, Model model) {
        model.addAttribute("impresariosDto", impresarioService.list().stream().map(ImpresarioDto::getName));
        model.addAttribute("genresDto", genreService.list().stream().map(GenreDto::getName));

        return "artist/new";
    }

    @GetMapping()
    public String getArtistList(Model model) {
        model.addAttribute("artistDto", artistService.list());
        return "artist/index";
    }

    // 2. Получить список артистов, выступающих в некотором жанре.
    @GetMapping(value = "by-genre")
    public ResponseEntity<List<ArtistDto>> getArtistsByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(artistService.findArtistsByGenre(genre));
    }

    // 3. Получить список артистов, работающих с некоторым импресарио.
    @GetMapping(value = "by-impresario")
    public ResponseEntity<List<ArtistDto>> getArtistsByImpresario(@RequestParam String impresario) {
        return ResponseEntity.ok(artistService.findArtistsByImpresario(impresario));
    }

    // 4. Получить список артистов, выступающих более чем в одним жанре с их
    //указанием.
    @GetMapping(value = "multiply-genres")
    public ResponseEntity<List<ArtistDto>> getArtistsWithMultiplyGenres() {
        return ResponseEntity.ok(artistService.findArtistsWithMultiplyGenres());
    }

    // 10. Получить список артистов, не участвовавших ни в каких конкурсах в
    //течение определенного периода времени.
    @GetMapping(value = "not-participated")
    public ResponseEntity<List<ArtistDto>> getArtistsNotParticipatedInCompetitions() {
        return ResponseEntity.ok(artistService.findArtistsNotParticipatedInCompetitions());
    }
}

