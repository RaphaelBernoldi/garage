package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.rest.response.PlateStatusResponse;
import br.com.estapar.garage.webhook.service.GarageSimService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/plate-status")
@Slf4j
public class PlateStatusController {

    private final GarageSimService garageSimService;

    /**
     * TODO - No exercicio foi solicitado utilizar POST na chamada, porém fiz com GET para ficar mais aderente com o padrão GET
     * e utilizei o PathVariable para o campo já ficar implicitamente obrigatório
     * @return
     */
    @GetMapping("/{license_plate}")
    public ResponseEntity<PlateStatusResponse> findStatusByPlate(@PathVariable("license_plate")String licensePlate) throws BusinessException {
        return ResponseEntity.ok(garageSimService.findStatusByPlate(licensePlate));
    }
}
