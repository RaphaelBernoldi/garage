package br.com.estapar.garage.webhook.rest;

import br.com.estapar.garage.webhook.exception.BusinessException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstranitViolationException(ConstraintViolationException e){
        log.error("Handling error {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException e){
        log.error("Handling error {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}
