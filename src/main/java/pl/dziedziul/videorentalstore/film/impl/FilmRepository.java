package pl.dziedziul.videorentalstore.film.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface FilmRepository extends JpaRepository<FilmEntity, UUID> {
    List<FilmEntity> findAllByOrderByDateCreated();

    Optional<FilmEntity> findByName(String name);
}
