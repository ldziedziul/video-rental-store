package pl.dziedziul.videorentalstore.rental.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.NonNull;

interface RentedFilmRepository extends JpaRepository<RentedFilmEntity, UUID> {
    Optional<RentedFilmEntity> findFirstByRentalEntity_IdAndFilmIdAndStatus(final @NonNull UUID rentalId, final @NonNull UUID filmId, final @NonNull RentalStatus status);
}
