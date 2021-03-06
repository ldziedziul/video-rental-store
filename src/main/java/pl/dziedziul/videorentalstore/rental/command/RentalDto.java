package pl.dziedziul.videorentalstore.rental.command;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("Rental")
public class RentalDto {
    UUID id;
    Integer price;
}
