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
        double result = 0d;
        result = calcLess25Percent(occupanceBalanceDTO.getTotalOccupance(), sector.getBasePrice(), result);
        result = calcBetween25And50Percent(occupanceBalanceDTO.getTotalOccupance(), sector.getBasePrice(), result);
        result = calcBetween50And75Percent(occupanceBalanceDTO.getTotalOccupance(), sector.getBasePrice(), result);
        result = calcBigger75Percent(occupanceBalanceDTO.getTotalOccupance(), sector.getBasePrice(), result);
        return result;
    }

    private double calcLess25Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance < 25 ?  basePrice - (basePrice * 0.1) :result;
    }
    private double calcBetween25And50Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance > 25 && totalOccupance <= 50 ?  basePrice : result;
    }
    private double calcBetween50And75Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance > 50 && totalOccupance <= 75 ?  basePrice + (basePrice * 0.1) : result;
    }
    private double calcBigger75Percent(double totalOccupance, double basePrice,  double result){
        return totalOccupance > 75 ?  basePrice + (basePrice * 0.25) : result;
    }
}
