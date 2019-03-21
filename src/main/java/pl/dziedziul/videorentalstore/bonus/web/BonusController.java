package pl.dziedziul.videorentalstore.bonus.web;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pl.dziedziul.videorentalstore.bonus.BonusPointsDto;
import pl.dziedziul.videorentalstore.bonus.BonusService;

@RestController
@RequestMapping(BonusController.BONUSES_PATH)
@Api(description = "Bonuses")
class BonusController {
    static final String BONUSES_PATH = "/bonuses";
    private final BonusService bonusService;

    BonusController(final BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping("/{customerId}")
    @ApiOperation("Get bonus points for customer")
    BonusPointsDto getBonusPoints(@PathVariable UUID customerId) {
        return new BonusPointsDto(bonusService.getCustomerBonusPoints(customerId));
    }
}
