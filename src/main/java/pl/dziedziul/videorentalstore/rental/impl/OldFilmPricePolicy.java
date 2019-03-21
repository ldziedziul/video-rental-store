package pl.dziedziul.videorentalstore.rental.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.film.FilmType;

@Component
class OldFilmPricePolicy implements FilmPricePolicy {

    private static final int FIRST_DAYS = 5;

    @Override
    public boolean supports(final @NonNull FilmType filmType) {
        return filmType == FilmType.OLD;
    }

    @Override
    public int calculatePrice(final int basePrice, final int days) {
        int price = basePrice;
        if (days > FIRST_DAYS) {
            price += (days - FIRST_DAYS) * basePrice;
        }
        return price;
    }
}
