package ru.nsu.fit.ekazakova.cityPhiharmonic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "home")
public class WelcomeController {

    @GetMapping()
    public String getHomePage() {
        return "index";
    }

    @PostMapping(params = "entity=genre")
    public String getGenrePage() {
        return "redirect:/genre";
    }

    @PostMapping(params = "entity=artist")
    public String getArtistPage() {
        return "redirect:/artist";
    }

    @PostMapping(params = "entity=event")
    public String getEventPage() {
        return "redirect:/event";
    }

    @PostMapping(params = "entity=queries")
    public String getSpecialQueries() {
        return "queries/index";
    }

    @PostMapping(value = "to-home")
    public String toHome(){
        return "queries/index";
    }

}
