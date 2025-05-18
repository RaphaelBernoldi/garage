package br.com.estapar.garage.webhook.model.dto;

public record ParkingSpotResponse(
        Integer id,
        String sector,
        Double lat,
        Double lng,
        Boolean occupied
) {
}
