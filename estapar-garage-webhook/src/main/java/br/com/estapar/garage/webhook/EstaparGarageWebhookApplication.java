package br.com.estapar.garage.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EstaparGarageWebhookApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstaparGarageWebhookApplication.class, args);
	}

}
