package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.dto.OccupanceBalanceDTO;
import br.com.estapar.garage.webhook.model.dto.ParkingSpotResponse;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import br.com.estapar.garage.webhook.repository.SpotRepository;
import br.com.estapar.garage.webhook.rest.response.SpotStatusResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    private final OccupationService occupationService;

    public OccupanceBalanceDTO getTotalOccupiedBySector(SectorEntity sector) {
        log.info("getting total occupation by sector {}", sector.getName());
        long totalOccupied = spotRepository
                .findBySector(sector)
                .stream()
                .filter(SpotEntity::getOccupied)
                .count();
        return OccupanceBalanceDTO
                .builder()
                .sector(sector.getName())
                .totalOccupance((totalOccupied / (double) sector.getMaxCapacity())* 100)
                .build();
    }

    public void createSpot(ParkingSpotResponse spotBySector, SectorEntity sector){
        log.info("Creating spot by sector {}", sector.getName());
        spotRepository
                .save(SpotEntity
                        .builder()
                        .id(spotBySector.id())
                        .lat(spotBySector.lat())
                        .occupied(Boolean.FALSE) // TODO - No response vem true, seria legal alterar a API
                        .lng(spotBySector.lng())
                        .sector(sector)
                        .build());
    }

    public SpotStatusResponse findByLatAndLnd(Double lat, Double lng) throws BusinessException {
        SpotEntity spot = findByLatAndLng(lat, lng);

        if(Boolean.TRUE.equals(spot.getOccupied())){
            OccupationEntity occupation = occupationService.findBySpot(spot);
            return SpotStatusResponse
                    .builder()
                    .entryTime(occupation.getEntryTime())
                    .ocupied(spot.getOccupied())
                    .timeParked(occupation.getParkedTime())
                    .build();
        }

        return SpotStatusResponse
                .builder()
                .ocupied(spot.getOccupied())
                .build();


    }


    public void save(SpotEntity spot) {
        log.info("saving spot");
        spotRepository.save(spot);
    }

    public SpotEntity findByLatAndLng(Double lat, Double lng) throws BusinessException {
        log.info("Finding spot by lat {} and lng {}", lat, lng);
        return spotRepository
                .findByLatAndLng(lat, lng)
                .orElseThrow(() -> new BusinessException("Spot not found"));
    }
}
