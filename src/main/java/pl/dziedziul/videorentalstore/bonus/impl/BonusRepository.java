package pl.dziedziul.videorentalstore.bonus.impl;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface BonusRepository extends JpaRepository<BonusEntity, UUID> {
}
