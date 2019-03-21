package pl.dziedziul.videorentalstore.bonus;

import java.util.UUID;

import lombok.NonNull;

public interface BonusService {
    int getCustomerBonusPoints(final @NonNull UUID customerId);
}
