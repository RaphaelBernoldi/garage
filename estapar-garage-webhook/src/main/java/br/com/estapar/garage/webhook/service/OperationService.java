package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.operations.ChooseOperation;
import br.com.estapar.garage.webhook.operations.Operation;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import br.com.estapar.garage.webhook.validation.groups.ValidationOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OperationService {
    private final ValidationOperation validationOperation;
    private final ChooseOperation chooseOperation;

    public void executeOperation(OperationGarageRequest request){
        log.info("Iniciando execucao da operacao");
        validationOperation.validateFields(request);
        Operation operation = chooseOperation.getOperation(request.getEventType());
        operation.process(request);
    }
}
