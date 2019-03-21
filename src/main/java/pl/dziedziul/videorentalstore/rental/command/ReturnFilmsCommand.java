package pl.dziedziul.videorentalstore.rental.command;

import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("ReturnFilms")
public class ReturnFilmsCommand {
    @NotNull
    UUID rentalId;
    @NotNull
    @Size(min = 1)
    Set<UUID> filmsToReturn;
}
