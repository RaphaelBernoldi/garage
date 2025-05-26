package br.com.estapar.garage.webhook.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SpotStatusResponse(
        @JsonProperty("ocupied") Boolean ocupied
        , @JsonProperty("entry_time") LocalDateTime entryTime
        , @JsonProperty("time_parked") LocalDateTime timeParked) {
}
