package br.com.estapar.garage.webhook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_sector")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorEntity {

    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private String name;
    private Double basePrice;
    private Integer maxCapacity;
    private String openHour;
    private String closeHour;
    private Integer durationLimitMinute;
    @Column(columnDefinition = "DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOperation;
    private Boolean opened;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        opened = Boolean.TRUE;
    }
}
