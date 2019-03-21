package pl.dziedziul.videorentalstore.film.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pl.dziedziul.videorentalstore.film.FilmDto;
import pl.dziedziul.videorentalstore.film.FilmService;

@RestController
@RequestMapping(FilmController.FILMS_PATH)
@Api(description = "Films")
class FilmController {

    static final String FILMS_PATH = "/films";

    private final FilmService filmService;

    FilmController(final FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    @ApiOperation("List all films")
    List<FilmDto> getFilms() {
        return filmService.getFilms();
    }
}
