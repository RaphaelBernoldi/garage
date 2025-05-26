package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.rest.response.RevenueResponse;
import br.com.estapar.garage.webhook.service.RevenueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/revenue")
@Slf4j
public class RevenueController {

    private final RevenueService revenueService;

    @GetMapping("/date/{date}/sector/{sector}")
    public ResponseEntity<RevenueResponse>findRevenueByDateAndSector(@PathVariable("date") LocalDate date, @PathVariable("sector") String sector){
        log.info("Receiveing request /revenue/date/{}/sector/{}", date, sector);
        return ResponseEntity.ok(revenueService.findRevenueByDateAndSector(date, sector));
    }
}
