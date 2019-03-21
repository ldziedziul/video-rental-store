package pl.dziedziul.videorentalstore.rental.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Clock;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.film.FilmDto;
import pl.dziedziul.videorentalstore.film.FilmService;

@Component
class SurchargeCalculator {
    private final FilmService filmService;
    private final FilmPriceCalculator filmPriceCalculator;
    private final Clock clock;

    SurchargeCalculator(final @NonNull FilmService filmService, final @NonNull FilmPriceCalculator filmPriceCalculator, final @NonNull Clock clock) {
        this.filmService = filmService;
        this.filmPriceCalculator = filmPriceCalculator;
        this.clock = clock;
    }

    int calculateSurcharge(final @NonNull RentedFilmEntity rentedFilm) {
        int actualRentalDurationInDays = rentalDurationInDays(rentedFilm);
        if (actualRentalDurationInDays <= rentedFilm.getDurationInDays()) {
            return 0;
        }
        FilmDto film = filmService.getFilm(rentedFilm.getFilmId());
        int priceForActualRental = filmPriceCalculator.calculatePrice(film, actualRentalDurationInDays);
        int paid = rentedFilm.getPrice();
        return Math.max(0, priceForActualRental - paid);
    }

    private int rentalDurationInDays(final @NonNull RentedFilmEntity rentedFilm) {
        LocalDate rentalDate = LocalDate.ofInstant(rentedFilm.getRentalEntity().getDateCreated(), clock.getZone());
        LocalDate returnDate = LocalDate.now(clock);
        return (int) DAYS.between(rentalDate, returnDate);
    }

}
