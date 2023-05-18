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
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.ArtistDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.ArtistService;

import java.util.List;

@RestController
@RequestMapping(value = "artist", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createArtist(@RequestBody ArtistDto artistDto) {
        artistService.createArtist(artistDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateArtist(@RequestBody ArtistDto artistDto, @PathVariable Long id) {
        artistService.updateArtist(artistDto, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistDto> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.findArtistById(id));
    }

    // 2. Получить список артистов, выступающих в некотором жанре.
    @GetMapping(value = "by-genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistDto>> getArtistsByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(artistService.findArtistsByGenre(genre));
    }

    // 3. Получить список артистов, работающих с некоторым импресарио.
    @GetMapping(value = "by-impresario", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistDto>> getArtistsByImpresario(@RequestParam String impresario) {
        return ResponseEntity.ok(artistService.findArtistsByImpresario(impresario));
    }

    // 4. Получить список артистов, выступающих более чем в одним жанре с их
    //указанием.
    @GetMapping(value = "multiply-genres", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistDto>> getArtistsWithMultiplyGenres() {
        return ResponseEntity.ok(artistService.findArtistsWithMultiplyGenres());
    }

    // 10. Получить список артистов, не участвовавших ни в каких конкурсах в
    //течение определенного периода времени.
    @GetMapping(value = "not-participated", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArtistDto>> getArtistsNotParticipatedInCompetitions() {
        return ResponseEntity.ok(artistService.findArtistsNotParticipatedInCompetitions());
    }
}

