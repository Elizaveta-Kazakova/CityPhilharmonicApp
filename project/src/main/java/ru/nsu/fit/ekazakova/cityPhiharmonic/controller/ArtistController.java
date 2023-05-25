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
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDetailsDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.CompetitionDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ImpresarioDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.ArtistNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.exception.GenreNotFoundException;
import ru.nsu.fit.ekazakova.cityPhiharmonic.repository.entity.artist.Artist;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ArtistService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.CompetitionService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.GenreService;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ImpresarioService;

import java.util.List;

@Controller
@RequestMapping(value = "artist")
public class ArtistController {
    private final ArtistService artistService;
    private final ImpresarioService impresarioService;
    private final GenreService genreService;
    private final CompetitionService competitionService;

    @Autowired
    public ArtistController(ArtistService artistService, ImpresarioService impresarioService,
                            GenreService genreService, CompetitionService competitionService) {
        this.artistService = artistService;
        this.impresarioService = impresarioService;
        this.genreService = genreService;
        this.competitionService = competitionService;

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

    @PostMapping(value = "by-genre")
    public String redirectToArtistsByGenre(@RequestParam String genre) {
        return "redirect:/artist/multiply-artists/" + genre + "?entity=genre";
    }

    @PostMapping(value = "prize-winners")
    public String redirectToArtistsByCompetition(@RequestParam String competition) {
        return "redirect:/artist/prize-winners/" + competition + "?entity=competition";
    }

    @PostMapping(value = "by-impresario")
    public String redirectToArtistsByImpresario(@RequestParam String impresario) {
        System.out.println("genre: " + impresario);
        List<ArtistDetailsDto> a = artistService.findArtistsByImpresario(impresario);
        System.out.println(a.get(0).getName());
        return "redirect:/artist/multiply-artists/" + impresario + "?entity=impresario";
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
    public String getUpdateArtistForm(@PathVariable("id") Long id, Model model) {
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

    @GetMapping("/by-genre")
    public String getArtistSearchByGenreForm(Model model) {
        model.addAttribute("genresDto", genreService.list().stream().map(GenreDto::getName));
        return "artist/by_genre";
    }

    @GetMapping("/by-impresario")
    public String getArtistSearchByImpresarioForm(Model model) {
        model.addAttribute("impresariosDto", impresarioService.list().stream().map(ImpresarioDto::getName));
        return "artist/by_impresario";
    }

    @GetMapping()
    public String getArtistList(Model model) {
        model.addAttribute("artistDto", artistService.list());
        model.addAttribute("genreName", "");
        return "artist/index";
    }

    // 2. Получить список артистов, выступающих в некотором жанре.
    @GetMapping(value = "/multiply-artists/{genre}", params = "entity=genre")
    public String getArtistsByGenre(@PathVariable String genre, Model model) {
        model.addAttribute("genresDto", genreService.list().stream().map(GenreDto::getName));
        model.addAttribute("artistsByGenre", artistService.findArtistsByGenre(genre));
        return "artist/by_genre";
    }

    // 3. Получить список артистов, работающих с некоторым импресарио.
    @GetMapping(value = "/multiply-artists/{impresario}", params = "entity=impresario")
    public String getArtistsByImpresario(@PathVariable String impresario, Model model) {
        model.addAttribute("impresariosDto", impresarioService.list().stream().map(ImpresarioDto::getName));
        model.addAttribute("artistsByImpresario", artistService.findArtistsByImpresario(impresario));
        return "artist/by_impresario";
    }


    // 4. Получить список артистов, выступающих более чем в одним жанре с их
    //указанием.
    @GetMapping("/multiply-genres")
    public String getArtistWithMultipleGenres(Model model) {
        model.addAttribute("artistsMultiplyGenresDto", artistService.findArtistsWithMultiplyGenres());
        return "artist/multiply_genres";
    }

//    7. Получить список призеров указанного конкурса.
    @GetMapping("/prize-winners")
    public String getArtistsByCompetitionForm(Model model) {
        model.addAttribute("competitionsDto",
                competitionService.list().stream().map(CompetitionDto::getName).toList());
        return "artist/prize_winners";
    }

    @GetMapping(value = "/prize-winners/{competition}", params = "entity=competition")
    public String getArtistsByCompetition(@PathVariable String competition, Model model) {
        model.addAttribute("competitionsDto",
                competitionService.list().stream().map(CompetitionDto::getName).toList());
        model.addAttribute("artistsByCompetition", artistService.findArtistsByCompetition(competition));
        return "artist/prize_winners";
    }

    // 10. Получить список артистов, не участвовавших ни в каких конкурсах в
    //течение определенного периода времени.
    @GetMapping(value = "not-participated")
    public ResponseEntity<List<ArtistDto>> getArtistsNotParticipatedInCompetitions() {
        return ResponseEntity.ok(artistService.findArtistsNotParticipatedInCompetitions());
    }
}

