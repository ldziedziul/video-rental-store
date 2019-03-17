package pl.dziedziul.videorentalstore.films;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("Film")
public class FilmDto {
    UUID id;
    String name;
    FilmType type;
}
