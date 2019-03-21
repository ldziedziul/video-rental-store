package pl.dziedziul.videorentalstore.rental.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import pl.dziedziul.videorentalstore.rental.RentalService;
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.RentalDto;

@RestController
@RequestMapping(RentalController.RENTALS_PATH)
@Api(description = "Rentals")
class RentalController {
    static final String RENTALS_PATH = "/rentals";

    private final RentalService rentalService;

    RentalController(final RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Rent films")
    RentalDto rentFilms(@RequestBody RentFilmsCommand command) {
        return rentalService.rentFilms(command);
    }
}
