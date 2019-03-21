package pl.dziedziul.videorentalstore.bonus.impl;

import java.time.Clock;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.dziedziul.videorentalstore.film.FilmDto;
import pl.dziedziul.videorentalstore.film.FilmService;
import pl.dziedziul.videorentalstore.film.FilmType;
import pl.dziedziul.videorentalstore.rental.FilmRentedEvent;

@Component
@Slf4j
class BonusCreator {
    private final FilmService filmService;
    private final BonusRepository bonusRepository;
    private final Clock clock;

    BonusCreator(final @NonNull FilmService filmService, final @NonNull BonusRepository bonusRepository, final @NonNull Clock clock) {
        this.filmService = filmService;
        this.bonusRepository = bonusRepository;
        this.clock = clock;
    }

    @Async
    @EventListener
    public void addBonusPoints(FilmRentedEvent event) {
        UUID customerId = event.getCustomerId();
        UUID filmId = event.getFilmId();
        int points = calculateBonusPoints(filmId);
        BonusEntity bonusEntity = new BonusEntity(customerId, filmId, points, clock.instant());
        log.info("Adding {} points for customer {}", points, customerId);
        bonusRepository.save(bonusEntity);
    }

    private int calculateBonusPoints(final UUID filmId) {
        FilmDto film = filmService.getFilm(filmId);
        if (film.getType() == FilmType.NEW) {
            return 2;
        }
        return 1;
    }
}
