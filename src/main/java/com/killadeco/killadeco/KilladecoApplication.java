package com.killadeco.killadeco;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class KilladecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KilladecoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		System.out.println("Zona horaria establecida: " + TimeZone.getDefault());
	}
}
