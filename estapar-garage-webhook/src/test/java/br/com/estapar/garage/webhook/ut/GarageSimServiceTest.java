package br.com.estapar.garage.webhook.ut;

import br.com.estapar.garage.webhook.client.GarageSimClient;
import br.com.estapar.garage.webhook.model.dto.GarageConfigResponse;
import br.com.estapar.garage.webhook.model.dto.GarageSectorResponse;
import br.com.estapar.garage.webhook.model.dto.ParkingSpotResponse;
import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.repository.SectorRepository;
import br.com.estapar.garage.webhook.service.GarageSimService;
import br.com.estapar.garage.webhook.service.SpotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static br.com.estapar.garage.webhook.buider.TestBuilder.oneGarageResponse;
import static br.com.estapar.garage.webhook.buider.TestBuilder.oneSpot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GarageSimServiceTest {

    @Mock
    private GarageSimClient garageSimClient;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private SpotService spotService;

    @InjectMocks
    private GarageSimService garageSimService;

    @Test
    void shouldFindAllConfigsGarage() {
        GarageConfigResponse response = new GarageConfigResponse(List.of(), List.of());
        when(garageSimClient.findAll()).thenReturn(response);

        GarageConfigResponse result = garageSimService.findAllConfigsGarage();

        assertEquals(response, result);
        verify(garageSimClient, times(1)).findAll();
    }

    @Test
    void shouldCreateSector() {
        GarageSectorResponse sectorResponse = oneGarageResponse();

        SectorEntity savedEntity = SectorEntity.builder()
                .name(sectorResponse.sector())
                .openHour(sectorResponse.openHour())
                .closeHour(sectorResponse.closeHour())
                .dateOperation(LocalDate.now())
                .basePrice(sectorResponse.basePrice())
                .maxCapacity(sectorResponse.maxCapacity())
                .durationLimitMinute(sectorResponse.durationLimitMinutes())
                .build();

        when(sectorRepository.save(any(SectorEntity.class))).thenReturn(savedEntity);

        SectorEntity result = garageSimService.createSector(sectorResponse);

        assertEquals(savedEntity.getName(), result.getName());
        verify(sectorRepository, times(1)).save(any(SectorEntity.class));
    }


    @Test
    void shouldSetupToday() {
        GarageSectorResponse sectorResponse = oneGarageResponse();
        ParkingSpotResponse spotResponse = oneSpot();

        GarageConfigResponse configResponse = new GarageConfigResponse(
                List.of(sectorResponse),
                List.of(spotResponse)
        );

        SectorEntity savedSector = SectorEntity.builder().name("A").build();

        when(garageSimClient.findAll()).thenReturn(configResponse);
        when(sectorRepository.save(any(SectorEntity.class))).thenReturn(savedSector);

        garageSimService.setupToday();

        verify(garageSimClient, times(2)).findAll();
        verify(sectorRepository, times(1)).save(any(SectorEntity.class));
        verify(spotService, times(1)).createSpot(any(ParkingSpotResponse.class), any(SectorEntity.class));
    }
}