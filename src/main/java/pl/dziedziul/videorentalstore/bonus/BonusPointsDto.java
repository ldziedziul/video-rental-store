package pl.dziedziul.videorentalstore.bonus;

import io.swagger.annotations.ApiModel;
import lombok.Value;

@Value
@ApiModel("Bonus points")
public class BonusPointsDto {
    int points;
}
