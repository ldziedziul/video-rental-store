package pl.dziedziul.videorentalstore.film;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    List<FilmDto> getFilms();

    /**
     * @throws FilmNotFoundException
     */
    FilmDto getFilm(UUID id);

    /**
     * @throws FilmNotFoundException
     */
    FilmDto getFilmByName(String name);

    FilmDto addFilm(AddFilmCommand addFilmCommand);
}
