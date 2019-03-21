package pl.dziedziul.videorentalstore.bonus.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import pl.dziedziul.videorentalstore.bonus.BonusService;

@Service
class DefaultBonusService implements BonusService {
    private final BonusRepository bonusRepository;

    DefaultBonusService(final BonusRepository bonusRepository) {
        this.bonusRepository = bonusRepository;
    }

    @Override
    public int getCustomerBonusPoints(final @NonNull UUID customerId) {
        return bonusRepository.getPointSumForCustomer(customerId);
    }

}
