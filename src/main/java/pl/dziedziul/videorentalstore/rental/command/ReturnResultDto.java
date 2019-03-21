package pl.dziedziul.videorentalstore.rental.command;


import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("ReturnResult")
public class ReturnResultDto {
    Integer surcharge;
}
