package pl.dziedziul.videorentalstore.rental.impl;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.films.FilmType;

class PriceLevelNotFoundException extends RuntimeException {
    public PriceLevelNotFoundException(final @NonNull FilmType filmType) {
        super("Price level for \"" + filmType + "\" not found");
    }
}
