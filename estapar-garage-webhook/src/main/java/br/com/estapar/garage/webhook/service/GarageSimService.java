package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.it.GarageSimClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GarageSimService {

    @Autowired
    private GarageSimClient garageSimClient;


}
