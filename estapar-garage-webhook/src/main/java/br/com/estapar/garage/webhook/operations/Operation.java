package br.com.estapar.garage.webhook.operations;

import br.com.estapar.garage.webhook.repository.OccupationRepository;
import br.com.estapar.garage.webhook.rest.request.OperationGarageRequest;

public interface Operation {
    void process(OperationGarageRequest request);
}
