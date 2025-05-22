package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.repository.OccupationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OccupationService {

    private final OccupationRepository occupationRepository;

    public void save(OccupationEntity occupationEntity){
        log.info("Saving occupation");
        occupationRepository.save(occupationEntity);

    }

    public OccupationEntity findByLicensePlate(String licensePlate) throws BusinessException {
        log.info("Finding occupation by license plate = {}", licensePlate);
        return occupationRepository
                .findByLicensePlate(licensePlate)
                .orElseThrow(() -> new BusinessException("Placa " + licensePlate + " nao encontrada"));
    }
}
