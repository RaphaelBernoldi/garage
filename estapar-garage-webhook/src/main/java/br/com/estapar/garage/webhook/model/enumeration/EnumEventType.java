package br.com.estapar.garage.webhook.model.enumeration;

import br.com.estapar.garage.webhook.operations.Operation;
import br.com.estapar.garage.webhook.operations.OperationEntry;
import br.com.estapar.garage.webhook.operations.OperationExit;
import br.com.estapar.garage.webhook.operations.OperationParked;
import lombok.Getter;

@Getter
public enum EnumEventType {

      ENTRY(new OperationEntry())
    , PARKED(new OperationParked())
    , EXIT(new OperationExit());

      private final Operation operation;

      EnumEventType(Operation operation){
          this.operation = operation;
      }

}
