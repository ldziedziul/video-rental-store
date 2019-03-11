package pl.dziedziul.videorentalstore.films.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.dziedziul.videorentalstore.films.FilmDto;
import pl.dziedziul.videorentalstore.films.FilmService;

@RestController
@RequestMapping(FilmController.FILMS_PATH)
class FilmController {

    static final String FILMS_PATH = "/films";

    private final FilmService filmService;

    FilmController(final FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    List<FilmDto> getFilms() {
        return filmService.getFilms();
    }
}
