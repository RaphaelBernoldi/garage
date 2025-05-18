package br.com.estapar.garage.webhook.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_spot")
public class SpotEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
    private UUID uuid;
    private Integer id;
    @JoinColumn(name = "id_sector", referencedColumnName = "uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private SectorEntity sector;
    private Double lat;
    private Double lng;
    private Boolean occupied;
}
