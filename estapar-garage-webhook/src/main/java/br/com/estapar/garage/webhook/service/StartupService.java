package br.com.estapar.garage.webhook.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartupService {

    @Autowired
    private GarageSimService garageSimService;

    private Logger log = LoggerFactory.getLogger(StartupService.class);

    @PostConstruct
    public void init(){
        log.info("Running start up service");
        garageSimService.setupToday();

    }
}
