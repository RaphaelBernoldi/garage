package br.com.estapar.garage.webhook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GarageConfigResponse(
        @JsonProperty("garage") List<GarageSectorResponse> garage,
        @JsonProperty("spots") List<ParkingSpotResponse> spots
) {}
