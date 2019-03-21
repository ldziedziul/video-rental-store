package pl.dziedziul.videorentalstore.rental.command;

import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("RentFilms")
public class RentFilmsCommand {
    @NotNull
    UUID customerId;
    @NotNull
    @Size(min = 1)
    Set<FilmToRent> filmsToRent;

    @Value
    @ApiModel("FilmToRent")
    public static class FilmToRent {
        @NotNull
        UUID filmId;
        @NotNull
        Integer durationInDays;
    }
}
