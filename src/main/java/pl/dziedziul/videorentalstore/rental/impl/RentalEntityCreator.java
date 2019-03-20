package pl.dziedziul.videorentalstore.rental.impl;

import java.time.Clock;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.films.FilmService;
import pl.dziedziul.videorentalstore.rental.RentFilmsCommand;

@Component
class RentalEntityCreator {
    private final FilmPriceCalculator filmPriceCalculator;
    private final FilmService filmService;
    private final Clock clock;

    RentalEntityCreator(final @NonNull FilmService filmService, final @NonNull FilmPriceCalculator filmPriceCalculator, final @NonNull Clock clock) {
        this.clock = clock;
        this.filmPriceCalculator = filmPriceCalculator;
        this.filmService = filmService;
    }

    RentalEntity create(final @NonNull RentFilmsCommand command) {
        RentalEntity rentalEntity = new RentalEntity(command.getCustomerId(), clock.instant());
        Set<RentedFilmEntity> rentedFilms = create(command.getFilmsToRent());
        rentedFilms.forEach(rentalEntity::withRentedFilm);
        return rentalEntity;
    }

    private Set<RentedFilmEntity> create(final Set<RentFilmsCommand.FilmToRent> filmsToRent) {
        return filmsToRent.stream()
            .map(this::create)
            .collect(Collectors.toSet());
    }

    private RentedFilmEntity create(final RentFilmsCommand.FilmToRent ftr) {
        return new RentedFilmEntity(ftr.getFilmId(), calculateFilmToRentPrice(ftr), ftr.getDurationInDays());
    }

    private int calculateFilmToRentPrice(final RentFilmsCommand.FilmToRent filmToRent) {
        UUID filmId = filmToRent.getFilmId();
        Integer durationInDays = filmToRent.getDurationInDays();
        return filmPriceCalculator.calculatePrice(filmService.getFilm(filmId), durationInDays);
    }
}
