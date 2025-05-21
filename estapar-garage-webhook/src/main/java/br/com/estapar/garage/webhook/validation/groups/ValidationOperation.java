package br.com.estapar.garage.webhook.validation.groups;

import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class ValidationOperation {

    private final Validator validator;

    public void validateFields(OperationGarageRequest request){
        log.info("Validating fields");
        Set<ConstraintViolation<OperationGarageRequest>> violations =

       switch (request.getEventType()){
           case ENTRY -> validator.validate(request, GroupEntry.class);
           case PARKED -> validator.validate(request, GroupParked.class);
           case EXIT -> validator.validate(request, GroupExit.class);
           default -> null;
       };

        if (Objects.nonNull(violations) && !violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

    }

}
