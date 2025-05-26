package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.repository.SectorRepository;
import br.com.estapar.garage.webhook.rest.response.RevenueResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class RevenueService {

    private final SectorRepository sectorRepository;

    public RevenueResponse findRevenueByDateAndSector(LocalDate date, String sector){
        log.info("Finding revenue by sector {}", sector);
        SectorEntity sectorEntity = sectorRepository.findByDateOperationAndName(date, sector);
        return RevenueResponse
                .builder()
                .amount(sectorEntity.getRevenue().getAmount())
                .currency(sectorEntity.getRevenue().getCurrency())
                .timestamp(LocalDateTime.now())
                .build();

    }
}
