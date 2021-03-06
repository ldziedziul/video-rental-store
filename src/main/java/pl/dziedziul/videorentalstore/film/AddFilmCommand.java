package pl.dziedziul.videorentalstore.film;

import lombok.NonNull;
import lombok.Value;

@Value
public class AddFilmCommand {
    @NonNull
    String name;
    @NonNull
    FilmType type;
}
