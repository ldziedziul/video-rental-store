package pl.dziedziul.videorentalstore.rental;

import java.util.UUID;

public class RentedFilmNotFoundException extends RuntimeException {
    public RentedFilmNotFoundException(final UUID rentalId, final UUID filmId) {
        super("Rented film not found. Rental = " + rentalId + ", film = " + filmId);
    }
}
