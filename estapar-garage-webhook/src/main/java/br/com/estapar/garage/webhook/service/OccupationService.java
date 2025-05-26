package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import br.com.estapar.garage.webhook.model.enumeration.EnumEventType;
import br.com.estapar.garage.webhook.repository.OccupationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OccupationService {

    private final OccupationRepository occupationRepository;

    public void save(OccupationEntity occupationEntity){
        log.info("Saving occupation");
        occupationRepository.save(occupationEntity);

    }
    public OccupationEntity findBySpot(SpotEntity spot) throws BusinessException {
        log.info("Finding occupation by spot lat = {}, lng = {}", spot.getLat(), spot.getLng());
        return occupationRepository.findBySpot(spot);
    }

    public OccupationEntity findByLicensePlateJoinFetchSpotAndSector(String licensePlate) throws BusinessException {
        log.info("Finding occupation by license plate = {}", licensePlate);
        return occupationRepository
                .findByLicensePlateJoinFetchSpotAndSector(licensePlate)
                .orElseThrow(() -> new BusinessException("Placa " + licensePlate + " nao encontrada"));
    }

    public OccupationEntity findByLicensePlateJoinFetchSpotAndSectorAndIsOccupied(String licensePlate) throws BusinessException {
        log.info("Finding occupation by license plate = {}", licensePlate);
        return occupationRepository
                .findByLicensePlateJoinFetchSpotAndSectorAndIsOccupied(licensePlate)
                .orElseThrow(() -> new BusinessException("Placa " + licensePlate + " nao encontrada"));
    }

    public OccupationEntity findByLicensePlate(String licensePlate) throws BusinessException {
        log.info("Finding occupation by license plate = {}", licensePlate);
        return occupationRepository
                .findByLicensePlateAndEventType(licensePlate, EnumEventType.ENTRY)
                .orElseThrow(() -> new BusinessException("Placa " + licensePlate + " nao encontrada na garagem"));
    }
}
