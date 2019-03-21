package pl.dziedziul.videorentalstore.rental.impl;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.dziedziul.videorentalstore.customer.CustomerService;
import pl.dziedziul.videorentalstore.rental.RentalService;
import pl.dziedziul.videorentalstore.rental.RentedFilmNotFoundException;
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.RentalDto;
import pl.dziedziul.videorentalstore.rental.command.ReturnFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.ReturnResultDto;

@Slf4j
@Service
class DefaultRentalService implements RentalService {

    private final CustomerService customerService;
    private final RentalRepository rentalRepository;
    private final RentalEntityCreator rentalEntityCreator;
    private final RentedFilmRepository rentedFilmRepository;
    private final SurchargeCalculator surchargeCalculator;

    DefaultRentalService(
        final @NonNull CustomerService customerService,
        final @NonNull RentalRepository rentalRepository,
        final @NonNull RentalEntityCreator rentalEntityCreator,
        final @NonNull RentedFilmRepository rentedFilmRepository, final SurchargeCalculator surchargeCalculator) {
        this.customerService = customerService;
        this.rentalRepository = rentalRepository;
        this.rentalEntityCreator = rentalEntityCreator;
        this.rentedFilmRepository = rentedFilmRepository;
        this.surchargeCalculator = surchargeCalculator;
    }

    @Override
    @Transactional
    public RentalDto rentFilms(final RentFilmsCommand command) {
        log.info("Renting films: " + command);
        validateCustomerExistence(command.getCustomerId());
        RentalEntity rentalEntity = rentalRepository.save(rentalEntityCreator.create(command));
        return new RentalDto(rentalEntity.getId(), rentalEntity.getTotalPrice());
    }

    @Override
    @Transactional
    public ReturnResultDto returnFilms(final ReturnFilmsCommand command) {
        log.info("Returning films: " + command);
        Set<RentedFilmEntity> filmsToReturn = getRentedFilms(command);
        markAsReturned(filmsToReturn);
        int surcharge = calculateSurcharge(filmsToReturn);
        return new ReturnResultDto(surcharge);
    }

    private Set<RentedFilmEntity> getRentedFilms(final ReturnFilmsCommand command) {
        UUID rentalId = command.getRentalId();
        return command.getFilmsToReturn().stream()
            .map(filmId -> getRentedFilm(rentalId, filmId))
            .collect(Collectors.toSet());
    }

    private RentedFilmEntity getRentedFilm(final UUID rentalId, final UUID filmId) {
        return rentedFilmRepository.findFirstByRentalEntity_IdAndFilmIdAndStatus(rentalId, filmId, RentalStatus.RENTED)
            .orElseThrow(() -> new RentedFilmNotFoundException(rentalId, filmId));
    }

    private void markAsReturned(final Set<RentedFilmEntity> filmsToReturn) {
        filmsToReturn.forEach(RentedFilmEntity::markAsReturned);
    }

    private int calculateSurcharge(final Set<RentedFilmEntity> filmsToReturn) {
        return filmsToReturn.stream().map(surchargeCalculator::calculateSurcharge).reduce(0, Integer::sum);
    }

    private void validateCustomerExistence(final UUID customerId) {
        customerService.getCustomer(customerId);
    }

}
