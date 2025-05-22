package br.com.estapar.garage.webhook.operations;

import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import br.com.estapar.garage.webhook.repository.OccupationRepository;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OperationEntry implements Operation{

    private final OccupationRepository repository;

    @Override
    public void process(OperationGarageRequest request) {
        log.info("Registrando entrada do cliente");

        repository
            .save(OccupationEntity
                    .builder()
                    .licensePlate(request.getLicensePlate())
                    .entryTime(request.getEntryTime())
                    .status("CREATED")
                    .eventType(EnumEventType.ENTRY)
                    .detail("O cliente entrou no estacionamento e está procurando vagas")
                    .build());
    }


}
