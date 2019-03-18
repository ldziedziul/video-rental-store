package pl.dziedziul.videorentalstore.films;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    List<FilmDto> getFilms();

    /**
     * @throws FilmNotFoundException
     */
    FilmDto getFilm(UUID id);

    FilmDto addFilm(AddFilmCommand addFilmCommand);
}
