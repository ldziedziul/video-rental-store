package pl.dziedziul.videorentalstore.customer.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.dziedziul.videorentalstore.customer.CustomerDto;
import pl.dziedziul.videorentalstore.customer.CustomerNotFoundException;
import pl.dziedziul.videorentalstore.customer.CustomerService;

@Slf4j
@Service
class DummyCustomerService implements CustomerService {

    @Override
    public CustomerDto getCustomer(final @NonNull UUID id) {
        if (!id.toString().endsWith("0")) {
            throw new CustomerNotFoundException(id);
        }
        return new CustomerDto(id, "customer-name");
    }
}
