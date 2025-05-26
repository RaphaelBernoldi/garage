package br.com.estapar.garage.webhook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_revenue")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueEntity {

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private String currency;
    private Double amount;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        this.amount = 0.0;
        this.currency = "R$";
    }
}
