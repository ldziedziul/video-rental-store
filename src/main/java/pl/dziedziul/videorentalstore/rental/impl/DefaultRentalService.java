package pl.dziedziul.videorentalstore.rental.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.dziedziul.videorentalstore.customer.CustomerService;
import pl.dziedziul.videorentalstore.rental.RentalService;
import pl.dziedziul.videorentalstore.rental.command.RentFilmsCommand;
import pl.dziedziul.videorentalstore.rental.command.RentalDto;

@Slf4j
@Service
class DefaultRentalService implements RentalService {

    private final CustomerService customerService;
    private final RentalRepository rentalRepository;
    private final RentalEntityCreator rentalEntityCreator;

    DefaultRentalService(
        final @NonNull CustomerService customerService,
        final @NonNull RentalRepository rentalRepository,
        final @NonNull RentalEntityCreator rentalEntityCreator) {
        this.customerService = customerService;
        this.rentalRepository = rentalRepository;
        this.rentalEntityCreator = rentalEntityCreator;
    }

    @Override
    @Transactional
    public RentalDto rentFilms(final RentFilmsCommand command) {
        log.info("Renting films: " + command);
        validateCustomerExistence(command.getCustomerId());
        RentalEntity rentalEntity = rentalRepository.save(rentalEntityCreator.create(command));
        return new RentalDto(rentalEntity.getId(), rentalEntity.getTotalPrice());
    }

    private void validateCustomerExistence(final UUID customerId) {
        customerService.getCustomer(customerId);
    }


}
