package br.com.estapar.garage.webhook.ut;

import br.com.estapar.garage.webhook.model.dto.ParkingSpotResponse;
import br.com.estapar.garage.webhook.model.entity.SectorEntity;
import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import br.com.estapar.garage.webhook.repository.SpotRepository;
import br.com.estapar.garage.webhook.service.SpotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.estapar.garage.webhook.buider.TestBuilder.oneSpot;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpotServiceTest {

    @Mock
    private SpotRepository spotRepository;

    @InjectMocks
    private SpotService spotService;

    @Test
    void shouldCreateSpot() {
        ParkingSpotResponse spotResponse = oneSpot();

        SectorEntity sector = SectorEntity.builder().name("A").build();

        when(spotRepository.save(any(SpotEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        spotService.createSpot(spotResponse, sector);

        verify(spotRepository, times(1)).save(any(SpotEntity.class));
    }


}
