package pl.dziedziul.videorentalstore.customer;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(final UUID id) {
        super("Customer with id = " + id + " not found");

    }
}
