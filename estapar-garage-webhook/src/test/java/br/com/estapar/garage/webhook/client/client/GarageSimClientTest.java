package br.com.estapar.garage.webhook.client.client;

import br.com.estapar.garage.webhook.client.GarageSimClient;
import br.com.estapar.garage.webhook.model.dto.GarageConfigResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GarageSimClientTest {

    @Autowired
    private GarageSimClient garageSimClient;

    @Test
    void giveFindData(){
        GarageConfigResponse configResponse = garageSimClient.findAll();
        assertFalse(configResponse.garage().isEmpty());
        assertFalse(configResponse.spots().isEmpty());

    }
}
