package pl.dziedziul.videorentalstore.rental;

import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.RentalDto;
import pl.dziedziul.videorentalstore.rental.command.ReturnFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.ReturnResultDto;

public interface RentalService {
    RentalDto rentFilms(RentFilmsCommand command);
}
