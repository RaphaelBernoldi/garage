package br.com.estapar.garage.webhook.repository;

import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OccupationRepository extends JpaRepository<OccupationEntity, UUID> {

    Optional<OccupationEntity> findByLicensePlateAndEventType(String licensePlate, EnumEventType eventType);

    @Query("SELECT o FROM OccupationEntity o " +
            "JOIN FETCH o.spot s " +
            "JOIN FETCH s.sector " +
            "WHERE o.licensePlate = :licensePlate")
    Optional<OccupationEntity>findByLicensePlateJoinFetchSpotAndSector(@Param("licensePlate")String licensePlate);
}
