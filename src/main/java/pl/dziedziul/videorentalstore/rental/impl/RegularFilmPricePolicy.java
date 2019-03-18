package pl.dziedziul.videorentalstore.rental.impl;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.films.FilmType;

@Component
class RegularFilmPricePolicy implements FilmPricePolicy {

    private static final int FIRST_DAYS = 3;

    @Override
    public boolean supports(final @NonNull FilmType filmType) {
        return filmType == FilmType.REGULAR;
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
