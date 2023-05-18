package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.fit.ekazakova.cityPhiharmonic.dto.GenreDto;
import ru.nsu.fit.ekazakova.cityPhiharmonic.service.GenreService;

@Controller
@RequestMapping(value = "genre")
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(value = "/new")
    public String redirectToCreateGenre() {
        return "redirect:/genre/new";
    }

    @PostMapping()
    public String createGenre(@ModelAttribute("genreDto") GenreDto genreDto) {
        genreService.createGenre(genreDto);
        return "redirect:/genre";
    }

    @PostMapping(value = "/update/{id}")
    public String updateGenre(@ModelAttribute("genreDto") GenreDto genreDto, @PathVariable Long id) {
        System.out.println("UPDATE");
        genreService.updateGenre(id, genreDto);
        return "redirect:/genre";
    }

    @GetMapping("/{id}")
    public String getGenreById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("genreDto", genreService.findGenreById(id));
        return "genre/show";
    }

    @GetMapping("/update/{id}")
    public String getUpdateGenreForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("genreDto", genreService.findGenreById(id));
        return "genre/update";
    }

    @PostMapping(value = "/{id}", params = "action=update")
    public String redirectToUpdateGenre() {
        return "redirect:/genre/update/{id}";
    }

    @PostMapping(value = "/{id}", params = "action=back")
    public String redirectToGenreList() {
        return "redirect:/genre";
    }

    @GetMapping()
    public String getGenreList(Model model) {
        model.addAttribute("genreDto", genreService.list());
        return "genre/index";
    }

    @GetMapping("/new")
    public String getGenreForm(@ModelAttribute("genreDto") GenreDto genreDto) {
        return "genre/new";
    }

}
