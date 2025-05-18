package br.com.estapar.garage.webhook.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_sector")
public class SectorEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
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
}
