package br.com.estapar.garage.webhook.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlateResponse(
          @JsonProperty("license_plate") String licensePlate
        , @JsonProperty("price_until_now") Double priceUntilNow
        , @JsonProperty("entry_time")LocalDateTime entryTime
        , @JsonProperty("time_parked")LocalDateTime timeParked
        ) {}
