package br.com.estapar.garage.webhook.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class StartupService {

    private final GarageSimService garageSimService;

    @PostConstruct
    public void init(){
        log.info("Running start up service");
        garageSimService.setupToday();

    }
}
