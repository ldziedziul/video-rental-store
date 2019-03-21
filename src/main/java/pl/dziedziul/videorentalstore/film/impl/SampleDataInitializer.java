package pl.dziedziul.videorentalstore.film.impl;

import static pl.dziedziul.videorentalstore.film.FilmType.NEW;
import static pl.dziedziul.videorentalstore.film.FilmType.OLD;
import static pl.dziedziul.videorentalstore.film.FilmType.REGULAR;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import pl.dziedziul.videorentalstore.film.AddFilmCommand;
import pl.dziedziul.videorentalstore.film.FilmService;

@Component
class SampleDataInitializer {
    private final FilmService filmService;

    SampleDataInitializer(final FilmService filmService) {
        this.filmService = filmService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void populate(ContextRefreshedEvent event) {
        filmService.addFilm(new AddFilmCommand("Matrix 11", NEW));
        filmService.addFilm(new AddFilmCommand("Spider Man", REGULAR));
        filmService.addFilm(new AddFilmCommand("Spider Man 2", REGULAR));
        filmService.addFilm(new AddFilmCommand("Ouf of Africa", OLD));
    }
}
