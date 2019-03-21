package pl.dziedziul.videorentalstore.bonus.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface BonusRepository extends JpaRepository<BonusEntity, UUID> {
    @Query("select coalesce(sum(b.points),0) from BonusEntity b where b.customerId = :customerId")
    int getPointSumForCustomer(@Param("customerId") UUID customerId);
}
