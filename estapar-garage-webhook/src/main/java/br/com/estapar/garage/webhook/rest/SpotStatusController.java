package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.service.GarageSimService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/spot-status")
@Slf4j
public class SpotStatusController {

    private final GarageSimService garageSimService;


}
