package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import br.com.estapar.garage.webhook.rest.request.OperationGarageResponse;
import br.com.estapar.garage.webhook.service.OperationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/webhook")
@Slf4j
public class WebhookController {

    private final OperationService operationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationGarageResponse>post(@Valid @RequestBody OperationGarageRequest body){
        log.info("Recebendo requisição POST /webhook body = {}", body);
        return ResponseEntity.ok(operationService.executeOperation(body));
    }
}
