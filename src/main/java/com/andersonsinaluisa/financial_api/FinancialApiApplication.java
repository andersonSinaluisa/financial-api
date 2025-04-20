package com.andersonsinaluisa.financial_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
@EnableR2dbcRepositories(basePackages = "com.andersonsinaluisa.financial_api.core.infrastructure.outbound.database")
public class FinancialApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialApiApplication.class, args);
	}

}
