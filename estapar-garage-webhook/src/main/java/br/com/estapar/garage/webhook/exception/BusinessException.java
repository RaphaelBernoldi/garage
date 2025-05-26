package br.com.estapar.garage.webhook.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {

    private String message;

    public BusinessException(String message){
        super(message);
        this.message = message;

    }
}
