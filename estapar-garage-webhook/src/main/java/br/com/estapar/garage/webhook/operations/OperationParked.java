package br.com.estapar.garage.webhook.operations;

import br.com.estapar.garage.webhook.exception.BusinessException;
import br.com.estapar.garage.webhook.model.entity.OccupationEntity;
import br.com.estapar.garage.webhook.model.entity.SpotEntity;
import br.com.estapar.garage.webhook.repository.OccupationRepository;
import br.com.estapar.garage.webhook.repository.SpotRepository;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;
import br.com.estapar.garage.webhook.service.OccupationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OperationParked implements Operation{

    private final OccupationService occupationService;
    private final SpotRepository spotRepository;

    /**
     * TODO - Pelo formato da request eu preciso ter como ponto de amarração lat e lng,
     * porém esse dado não me parece ser muito seguro então em um caso real eu levantaria a ideia de de repente usarmos códigos para as vagas
     * porém como é um caso apenas de teste, vou considerar que o dado seja seguro para se trabalhar
     * @return
     */
    @Override
    public void process(OperationGarageRequest request) {
        try{
            log.info("Iniciando operação parked para a placa " + request.getLicensePlate());
            OccupationEntity occupationEntity = occupationService.findByLicensePlate(request.getLicensePlate());
            SpotEntity spot = spotRepository.findByLatAndLng(request.getLat(), request.getLng());
            validateSpotOccupied(spot);
            spot.setOccupied(Boolean.TRUE);
            occupationEntity.setEventType(request.getEventType());
            occupationEntity.setStatus("Success");
            occupationEntity.setDetail("Estacionou sem problemas");
            occupationEntity.setParkedTime(LocalDateTime.now());

            if(Objects.isNull(spot)){ //TODO - Esse if corrige a falha na operação conforme citado comentario acima
                occupationEntity.setStatus("Vaga nao encontrada");
                occupationEntity.setDetail("O cliente estacionou em um vaga desconhecida com lat = " + request.getLat() + " e lng = " + request.getLng());
                occupationService.save(occupationEntity);
                return;
            }
            spotRepository.save(spot);
            occupationEntity.setSpot(spot);
            occupationService.save(occupationEntity);

        }catch (BusinessException e){
            log.error("Erro na operacao parked {}", e.getMessage());
            occupationService
                    .save(OccupationEntity
                            .builder()
                            .entryTime(request.getEntryTime())
                            .eventType(request.getEventType())
                            .licensePlate(request.getLicensePlate())
                            .status("Parked com problema")
                            .detail("Falha ao estacionar na vaga lat = " + request.getLat() + " e lng = " + request.getLng() + " erro -> "  + e.getMessage())
                            .build());
        }
    }

    private void validateSpotOccupied(SpotEntity spot) throws BusinessException {
        if(spot.getOccupied()){
            throw new BusinessException("A vaga esta ocupada");
        }
    }
}
