package pl.dziedziul.videorentalstore.films.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface FilmRepository extends JpaRepository<FilmEntity, UUID> {
    List<FilmEntity> findAllByOrderByDateCreated();
}
