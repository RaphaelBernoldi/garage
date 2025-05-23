package br.com.estapar.garage.webhook.repository;

import br.com.estapar.garage.webhook.model.entity.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RevenueRepository extends JpaRepository<RevenueEntity, UUID> {
}
