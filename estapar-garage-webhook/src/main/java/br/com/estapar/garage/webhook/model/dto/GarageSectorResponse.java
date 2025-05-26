package br.com.estapar.garage.webhook.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record GarageSectorResponse(
        @JsonProperty("sector") String sector,
        @JsonProperty("base_price")Double basePrice,
        @JsonProperty("max_capacity") Integer maxCapacity,
        @JsonProperty("open_hour") String openHour,
        @JsonProperty("close_hour") String closeHour,
        @JsonProperty("duration_limit_minutes") Integer durationLimitMinutes
) {}
