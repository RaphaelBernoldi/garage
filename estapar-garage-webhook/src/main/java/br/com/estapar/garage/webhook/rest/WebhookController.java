package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.rest.request.EntryGarageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@Slf4j
public class WebhookController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>post(@RequestBody EntryGarageRequest body){
        log.info(body.toString());
        return ResponseEntity.ok("chegou o dado");
    }
}
