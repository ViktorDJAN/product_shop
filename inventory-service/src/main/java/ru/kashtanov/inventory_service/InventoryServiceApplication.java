package ru.kashtanov.inventory_service;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaNamespace;
import jakarta.websocket.ClientEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InventoryServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
