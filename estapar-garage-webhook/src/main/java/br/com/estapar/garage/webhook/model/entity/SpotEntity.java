package br.com.estapar.garage.webhook.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_spot")
public class SpotEntity {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private Integer id;
    @JoinColumn(name = "id_sector", referencedColumnName = "uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private SectorEntity sector;
    private Double lat;
    private Double lng;
    private Boolean occupied;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}
