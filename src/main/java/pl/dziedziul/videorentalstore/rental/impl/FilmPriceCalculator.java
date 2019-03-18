package pl.dziedziul.videorentalstore.rental.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.films.FilmDto;
import pl.dziedziul.videorentalstore.films.FilmType;

@Component
class FilmPriceCalculator {
    private final RentalProperties rentalProperties;
    private final PriceLevelMapper priceLevelMapper;
    private final List<FilmPricePolicy> pricePolicies;

    FilmPriceCalculator(final @NonNull RentalProperties rentalProperties,
                        final @NonNull PriceLevelMapper priceLevelMapper,
                        final @NonNull List<FilmPricePolicy> pricePolicies) {
        this.priceLevelMapper = priceLevelMapper;
        this.rentalProperties = rentalProperties;
        this.pricePolicies = pricePolicies;
    }

    int calculatePrice(final @NonNull FilmDto film, final int days) {
        FilmType filmType = film.getType();
        PriceLevel priceLevel = priceLevelMapper.mapToPriceType(filmType);
        int basePrice = getBasePrice(priceLevel);
        FilmPricePolicy pricePolicy = getPricePolicy(filmType);
        return pricePolicy.calculatePrice(basePrice, days);
    }

    private FilmPricePolicy getPricePolicy(final FilmType filmType) {
        return pricePolicies.stream()
            .filter(p -> p.supports(filmType))
            .findFirst()
            .orElseThrow(() -> new PricePolicyNotFoundException(filmType));
    }

    private int getBasePrice(final PriceLevel priceLevel) {
        int basePrice = Optional.ofNullable(rentalProperties.getPrices().get(priceLevel))
            .orElseThrow(() -> new PriceNotFoundException(priceLevel));
        Assert.isTrue(basePrice >= 0, "Base price must not be negative");
        return basePrice;
    }

}
