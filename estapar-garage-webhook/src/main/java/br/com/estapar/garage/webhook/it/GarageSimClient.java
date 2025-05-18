package br.com.estapar.garage.webhook.it;

import br.com.estapar.garage.webhook.model.dto.GarageConfigResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${app.feign.host.garage-sim}"
           , value = "garage-sim"
           , path = "/garage")
public interface GarageSimClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    GarageConfigResponse findAll();

}
