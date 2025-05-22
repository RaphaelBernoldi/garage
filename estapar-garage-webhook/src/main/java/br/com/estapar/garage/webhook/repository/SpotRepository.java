package br.com.estapar.garage.webhook.repository;

import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, UUID> {
    SpotEntity findByLatAndLng(Double lat, Double lng);
    List<SpotEntity>findBySector(SectorEntity sector);
}
