package br.com.estapar.garage.webhook.repository;

import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OccupationRepository extends JpaRepository<OccupationEntity, UUID> {

    Optional<OccupationEntity> findByLicensePlate(String licensePlate);
}
