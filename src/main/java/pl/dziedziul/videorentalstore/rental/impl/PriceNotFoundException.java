package pl.dziedziul.videorentalstore.rental.impl;

import lombok.NonNull;

class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(final @NonNull PriceLevel priceLevel) {
        super("Price for \"" + priceLevel + "\" not found");
    }
}
