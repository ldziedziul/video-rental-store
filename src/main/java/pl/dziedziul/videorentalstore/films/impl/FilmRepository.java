package pl.dziedziul.videorentalstore.films.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface FilmRepository extends JpaRepository<FilmEntity, UUID> {

}
