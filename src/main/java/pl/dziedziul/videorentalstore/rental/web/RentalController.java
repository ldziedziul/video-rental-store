package pl.dziedziul.videorentalstore.rental.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pl.dziedziul.videorentalstore.rental.RentalService;
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.RentalDto;
import pl.dziedziul.videorentalstore.rental.command.ReturnFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.ReturnResultDto;

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
    RentalDto rentFilms(@Valid @RequestBody RentFilmsCommand command) {
        return rentalService.rentFilms(command);
    }

    @PostMapping("/returns")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Return rented films")
    ReturnResultDto returnFilms(@Valid @RequestBody ReturnFilmsCommand command) {
        return rentalService.returnFilms(command);
    }
}
