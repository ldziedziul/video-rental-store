package pl.dziedziul.videorentalstore.rental.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.films.FilmType;

@Component
class NewFilmPricePolicy implements FilmPricePolicy {
    @Override
    public boolean supports(final @NonNull FilmType filmType) {
        return filmType == FilmType.NEW;
    }

    @Override
    public int calculatePrice(final int basePrice, final int days) {
        return basePrice * days;
    }
}
