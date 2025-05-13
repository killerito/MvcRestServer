package com.azienda.esercizioSpringWebData.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.azienda.esercizioSpringWebData"})
@EnableJpaRepositories(basePackages = {"com.azienda.esercizioSpringWebData.repository"})
@EntityScan(basePackages = {"com.azienda.esercizioSpringWebData.model"})
public class EsercizioSpringWebDataApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EsercizioSpringWebDataApplication.class, args);
			System.out.println("Connesione al db riuscita");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
