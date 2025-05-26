package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.rest.response.SpotStatusResponse;
import br.com.estapar.garage.webhook.service.GarageSimService;
import br.com.estapar.garage.webhook.service.SpotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/spot-status")
@Slf4j
public class SpotStatusController {

    private final SpotService spotService;

    /**
     * TODO - No exercicio foi solicitado utilizar POST na chamada, porém fiz com GET para ficar mais aderente com o padrão GET
     * e utilizei o PathVariable para o campo já ficar implicitamente obrigatório
     * @return
     */
    @GetMapping("/lat/{lat}/lng/{lng}")
    public ResponseEntity<SpotStatusResponse>findByLatAndLng(@PathVariable("lat") Double lat, @PathVariable("lng") Double lng) throws BusinessException {
        log.info("Recebendo requisicao /spot-status/lat/{}/lng/{}", lat, lng);
        return ResponseEntity.ok(spotService.findByLatAndLnd(lat, lng));
    }


}
