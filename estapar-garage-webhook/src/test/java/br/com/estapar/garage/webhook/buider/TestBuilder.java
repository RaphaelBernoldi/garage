package br.com.estapar.garage.webhook.buider;

import br.com.estapar.garage.webhook.model.dto.GarageSectorResponse;
import br.com.estapar.garage.webhook.model.dto.ParkingSpotResponse;

public class TestBuilder {

    public static GarageSectorResponse oneGarageResponse(){
        return new GarageSectorResponse("A",10.0, 10,"08:00", "23:59", 120);
    }

    public static ParkingSpotResponse oneSpot(){
        return new ParkingSpotResponse(1, "A", 20.123456, 15.123456, true);
    }
}
