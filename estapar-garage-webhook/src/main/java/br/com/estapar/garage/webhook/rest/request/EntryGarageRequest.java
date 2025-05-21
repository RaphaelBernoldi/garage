package br.com.estapar.garage.webhook.rest.request;

import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EntryGarageRequest {

    @JsonProperty("license_plate")
    private String licensePlate;
    @JsonProperty("lat")
    private Long lat;
    @JsonProperty("lng")
    private Long lng;
    @Enumerated(EnumType.STRING)
    @JsonProperty("event_type")
    private EnumEventType eventType;
}
