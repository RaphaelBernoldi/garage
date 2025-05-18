package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.it.GarageSimClient;
import br.com.estapar.garage.webhook.model.dto.GarageConfigResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartupService {

    @Autowired
    private GarageSimClient garageSimClient;

    private Logger log = LoggerFactory.getLogger(StartupService.class);

    @PostConstruct
    public void init(){
        log.info("Application up finding garage data");
        GarageConfigResponse config = garageSimClient.findAll();
    }
}
