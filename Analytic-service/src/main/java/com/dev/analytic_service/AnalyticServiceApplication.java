package com.dev.analytic_service;

import com.dev.analytic_service.Controllers.FakeDBController;
import com.dev.analytic_service.Services.FakeDBService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class AnalyticServiceApplication {//implements Runnable{

	public static void main(String[] args) {
		SpringApplication.run(AnalyticServiceApplication.class, args);
		System.out.println("Application Started");
	}
}
