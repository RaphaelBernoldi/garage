package br.com.estapar.garage.webhook.service;

import br.com.estapar.garage.webhook.client.GarageSimClient;
import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.dto.GarageConfigResponse;
import br.com.estapar.garage.webhook.model.dto.GarageSectorResponse;
import br.com.estapar.garage.webhook.model.dto.OccupanceBalanceDTO;
import br.com.estapar.garage.webhook.model.dto.ParkingSpotResponse;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.entity.RevenueEntity;
import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import br.com.estapar.garage.webhook.repository.SectorRepository;
import br.com.estapar.garage.webhook.repository.SpotRepository;
import br.com.estapar.garage.webhook.rest.response.PlateStatusResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static br.com.estapar.garage.webhook.operations.CalculatorPrice.calcComplete;

@Service
@Slf4j
@AllArgsConstructor
public class GarageSimService {

    private final GarageSimClient garageSimClient;

    private final SectorRepository sectorRepository;

    private final SpotRepository spotRepository;

    private final OccupationService occupationService;

    public GarageConfigResponse findAllConfigsGarage(){
        log.info("Finding today setup garage");
        return garageSimClient.findAll();
    }

    public PlateStatusResponse findStatusByPlate(String licensePlate) throws BusinessException {
        log.info("Finding status by plate");
        OccupationEntity occupation = occupationService.findByLicensePlateJoinFetchSpotAndSectorAndIsOccupied(licensePlate);

        SectorEntity sector = occupation.getSpot().getSector();
        OccupanceBalanceDTO totalOccupiedBySector = getTotalOccupiedBySector(sector);

        return PlateStatusResponse
                .builder()
                .entryTime(occupation.getEntryTime())
                .timeParked(occupation.getParkedTime())
                .licensePlate(licensePlate)
                .priceUntilNow(calcComplete(totalOccupiedBySector.getTotalOccupance(), sector.getBasePrice()))
                .build();
    }

    public List<OccupanceBalanceDTO> getOccupance(){
        log.info("Finding occupance resume");
        return sectorRepository
                .findByDateOperation(LocalDate.now())
                .stream()
                .map(this::getTotalOccupiedBySector)
                .toList();
    }

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


    @Transactional
    public SectorEntity createSector(GarageSectorResponse sectorResponse){
        log.info("Creating sector...");
        return sectorRepository
                .save(SectorEntity
                        .builder()
                        .name(sectorResponse.sector())
                        .openHour(sectorResponse.openHour())
                        .closeHour(sectorResponse.closeHour())
                        .dateOperation(LocalDate.now())
                        .basePrice(sectorResponse.basePrice())
                        .maxCapacity(sectorResponse.maxCapacity())
                        .durationLimitMinute(sectorResponse.durationLimitMinutes())
                        .revenue(RevenueEntity
                                    .builder()
                                    .build())
                        .build());
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

    private void createSpotsBySector(List<ParkingSpotResponse>spotsResponse, SectorEntity sector){
        spotsResponse
                .stream()
                .filter(spot -> spot.sector().equals(sector.getName()))
                .forEach(spotsBySector -> createSpot(spotsBySector, sector));
    }

    @Transactional
    public void setupToday(){
        GarageConfigResponse configResponse = findAllConfigsGarage();
        findAllConfigsGarage()
                .garage()
                .stream()
                .map(this::createSector)
                .forEach(sectorSaved -> createSpotsBySector(configResponse.spots(), sectorSaved));
    }
}
