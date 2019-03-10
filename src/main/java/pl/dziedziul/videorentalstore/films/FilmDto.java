package pl.dziedziul.videorentalstore.films;

import java.util.UUID;

import lombok.Value;

@Value
public class FilmDto {
    UUID id;
    String name;
    FilmType type;
}
