package br.com.atv.projeto_ci_inicial_lucasgf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoCiInicialLucasgfApplication {

	public static void main(String[] args) {
		System.out.println("Iniciando aplicacao com Docker");
		SpringApplication.run(ProjetoCiInicialLucasgfApplication.class, args);
	}

}
