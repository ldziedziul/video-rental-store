package pl.dziedziul.videorentalstore.rental.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface RentalRepository extends JpaRepository<RentalEntity, UUID> {
}
