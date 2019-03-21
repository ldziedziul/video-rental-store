package pl.dziedziul.videorentalstore.rental.impl;

import pl.dziedziul.videorentalstore.film.FilmType;

class PricePolicyNotFoundException extends RuntimeException {
    public PricePolicyNotFoundException(final FilmType filmType) {
        super("Price policy for \"" + filmType + "\" not found");
    }
}
