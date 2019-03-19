package pl.dziedziul.videorentalstore.customer;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("Customer")
public class CustomerDto {
    UUID id;
    String name;
}
