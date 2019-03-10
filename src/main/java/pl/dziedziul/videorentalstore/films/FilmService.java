package pl.dziedziul.videorentalstore.films;

import java.util.List;

public interface FilmService {
    List<FilmDto> getFilms();

    FilmDto addFilm(AddFilmCommand addFilmCommand);
}
