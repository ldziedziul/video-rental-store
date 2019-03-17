package pl.dziedziul.videorentalstore.rental;

public interface RentalService {
    RentalDto rentFilms(RentFilmsCommand command);
}
