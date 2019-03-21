package pl.dziedziul.videorentalstore.rental.impl;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.film.FilmType;

@Component
class PriceLevelMapper {
    private final RentalProperties rentalProperties;

    PriceLevelMapper(final @NonNull RentalProperties rentalProperties) {
        this.rentalProperties = rentalProperties;
    }

    PriceLevel mapToPriceType(final @NonNull FilmType filmType) {
        return Optional.ofNullable(this.rentalProperties.getPriceLevelMapping()
            .get(filmType))
            .orElseThrow(() -> new PriceLevelNotFoundException(filmType));
    }

    @PostConstruct
    void validateConfig() {
        for (FilmType filmType : FilmType.values()) {
            mapToPriceType(filmType);
        }
    }
}
