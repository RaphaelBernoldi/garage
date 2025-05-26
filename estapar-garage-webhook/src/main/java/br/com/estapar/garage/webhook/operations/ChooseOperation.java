package br.com.estapar.garage.webhook.operations;

import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChooseOperation {

    private final OperationEntry operationEntry;
    private final OperationParked operationParked;
    private final OperationExit operationExit;

    public Operation getOperation(EnumEventType eventType){
        return
                switch (eventType){
                    case ENTRY -> operationEntry;
                    case PARKED -> operationParked;
                    case EXIT -> operationExit;
                };
    }
}
