package pl.dziedziul.videorentalstore.films.impl;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.dziedziul.videorentalstore.films.AddFilmCommand;
import pl.dziedziul.videorentalstore.films.FilmDto;
import pl.dziedziul.videorentalstore.films.FilmService;

@Service
@Slf4j
class DefaultFilmService implements FilmService {

    private final FilmRepository filmRepository;
    private final Clock clock;

    DefaultFilmService(final FilmRepository filmRepository, final Clock clock) {
        this.filmRepository = filmRepository;
        this.clock = clock;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDto> getFilms() {
        List<FilmDto> films = filmRepository.findAllByOrderByDateCreated().stream().map(this::toDto)
            .collect(Collectors.toList());
        log.info("Returning films: {}", films);
        return films;
    }

    @Override
    @Transactional
    public FilmDto addFilm(final AddFilmCommand command) {
        log.info("Adding new film: {}", command);
        FilmEntity filmEntity = filmRepository.save(toEntity(command));
        return toDto(filmEntity);
    }

    private FilmEntity toEntity(final AddFilmCommand command) {
        return new FilmEntity(command.getName(), command.getType(), clock.instant());
    }

    private FilmDto toDto(final FilmEntity filmEntity) {
        return new FilmDto(filmEntity.getId(), filmEntity.getName(), filmEntity.getType());
    }
}
