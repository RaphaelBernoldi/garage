package br.com.estapar.garage.webhook.repository;

import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, UUID> {
}
