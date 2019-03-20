package pl.dziedziul.videorentalstore.films;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(final String column, final Object id) {
        super("Film with " + column + " = " + id + " not found");
    }
}
