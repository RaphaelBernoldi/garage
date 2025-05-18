package br.com.estapar.garage.webhook.model.entity;

import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_occupation")
public class OccupationEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false)
    private UUID uuid;

    private String licensePlate;

    @Column(columnDefinition = "TIMESTAMP")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime entryTime;

    @Enumerated(EnumType.STRING)
    private EnumEventType eventType;

    @Column(columnDefinition = "TIMESTAMP")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    @JoinColumn(name = "id_spot", referencedColumnName = "uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private SpotEntity spot;

    private String detail;

    private String status;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
