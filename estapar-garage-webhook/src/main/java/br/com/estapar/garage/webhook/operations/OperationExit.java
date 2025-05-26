package br.com.estapar.garage.webhook.operations;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.dto.OccupanceBalanceDTO;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.entity.RevenueEntity;
import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.repository.RevenueRepository;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import br.com.estapar.garage.webhook.service.GarageSimService;
import br.com.estapar.garage.webhook.service.OccupationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.estapar.garage.webhook.operations.CalculatorPrice.*;

@Service
@AllArgsConstructor
@Slf4j
public class OperationExit implements Operation{

    private final OccupationService occupationService;
    private final GarageSimService garageSimService;
    private final RevenueRepository revenueRepository;

    @Transactional
    @Override
    public void process(OperationGarageRequest request) {
        try {
            OccupationEntity occupationEntity = occupationService.findByLicensePlateJoinFetchSpotAndSector(request.getLicensePlate());
            occupationEntity.setEventType(request.getEventType());
            occupationEntity.getSpot().setOccupied(Boolean.FALSE);
            SectorEntity sector = occupationEntity.getSpot().getSector();
            RevenueEntity revenue = sector.getRevenue();
            revenue.setAmount(sector.getRevenue().getAmount() + calculatePrice(sector));
            revenueRepository.save(revenue);

        } catch (BusinessException e) {
            log.error("Erro {}", e.getMessage());
        }
    }

    private Double calculatePrice(SectorEntity sector) throws BusinessException {
        List<OccupanceBalanceDTO> occupance = garageSimService.getOccupance();
        OccupanceBalanceDTO occupanceBalanceDTO = occupance
                                                    .stream()
                                                    .peek(o -> o.getSector().equals(sector.getName()))
                                                    .findAny()
                                                    .orElseThrow(() -> new BusinessException("Falha ao calcular valor, setor nao encontrado"));
        return calcComplete(occupanceBalanceDTO.getTotalOccupance(), sector.getBasePrice());
    }


}
