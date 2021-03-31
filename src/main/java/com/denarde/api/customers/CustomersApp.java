package com.denarde.api.customers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Customers API", version = "1.0", description = ""))
public class CustomersApp {

	public static void main(String[] args) {
		SpringApplication.run(CustomersApp.class, args);
	}

}
