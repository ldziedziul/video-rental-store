package pl.dziedziul.videorentalstore.customer;

import java.util.UUID;

public interface CustomerService {
    /**
     * @throws CustomerNotFoundException
     */
    CustomerDto getCustomer(UUID id);
}
