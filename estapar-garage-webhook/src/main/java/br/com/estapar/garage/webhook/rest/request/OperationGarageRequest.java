package br.com.estapar.garage.webhook.rest.request;

import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import br.com.estapar.garage.webhook.validation.groups.GroupEntry;
import br.com.estapar.garage.webhook.validation.groups.GroupExit;
import br.com.estapar.garage.webhook.validation.groups.GroupParked;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationGarageRequest {

    @NotNull(message = "Placa é obrigatoria")
    @JsonProperty("license_plate")
    private String licensePlate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    @NotNull(message = "Hora de entrada é obrigatoria para essa operacao", groups = {GroupEntry.class})
    @JsonProperty("entry_time")
    private LocalDateTime entryTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    @NotNull(message = "Hora de saida é obrigatoria para essa operacao", groups = {GroupExit.class})
    @JsonProperty("exit_time")
    private LocalDateTime exitTime;

    @NotNull(message = "Lat é obrigatoria para essa operacao", groups = {GroupParked.class})
    @JsonProperty("lat")
    private Double lat;

    @NotNull(message = "Lng é obrigatoria para essa operacao", groups = {GroupParked.class})
    @JsonProperty("lng")
    private Double lng;

    @NotNull(message = "Eventp é obrigatorio")
    @Enumerated(EnumType.STRING)
    @JsonProperty("event_type")
    private EnumEventType eventType;
}
