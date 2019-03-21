package pl.dziedziul.videorentalstore.rental.impl;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.film.FilmType;

interface FilmPricePolicy {
    boolean supports(final @NonNull FilmType filmType);

    int calculatePrice(final int basePrice, final int days);
}
