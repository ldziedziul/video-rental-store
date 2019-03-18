package pl.dziedziul.videorentalstore.films;

import java.util.UUID;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(final UUID id) {
        super("Film with id = " + id + " not found");
    }
}
