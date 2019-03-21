package pl.dziedziul.videorentalstore.rental.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import pl.dziedziul.videorentalstore.film.FilmType;

@Component
@ConfigurationProperties(prefix = "rental")
class RentalProperties {
    @Getter
    private Map<PriceLevel, Integer> prices = new HashMap<>();
    @Getter
    private Map<FilmType, PriceLevel> priceLevelMapping = new HashMap<>();

    protected RentalProperties() {
    }

    public RentalProperties(final @NonNull Map<PriceLevel, Integer> prices, final @NonNull Map<FilmType, PriceLevel> priceLevelMapping) {
        this.prices = prices;
        this.priceLevelMapping = priceLevelMapping;
    }
}
