package br.com.estapar.garage.webhook.rest.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RevenueResponse(Double amount, String currency, LocalDateTime timestamp) {
}
