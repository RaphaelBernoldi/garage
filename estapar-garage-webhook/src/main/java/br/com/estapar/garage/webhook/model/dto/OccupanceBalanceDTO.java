package br.com.estapar.garage.webhook.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OccupanceBalanceDTO {

    private String sector;
    private Long totalOccupance;
}
