package br.com.estapar.garage.webhook.operations;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.dto.OccupanceBalanceDTO;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import br.com.estapar.garage.webhook.service.GarageSimService;
import br.com.estapar.garage.webhook.service.OccupationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OperationExit implements Operation{

    private final OccupationService occupationService;
    private final GarageSimService garageSimService;

    @Override
    public void process(OperationGarageRequest request) {
        try {
            OccupationEntity occupationEntity = occupationService.findByLicensePlate(request.getLicensePlate());
            occupationEntity.setEventType(request.getEventType());
            List<OccupanceBalanceDTO> occupance = garageSimService.getOccupance();



        } catch (BusinessException e) {
            throw new RuntimeException(e);
        }

    }
}
