package se.iths.martin.orderserviceprojekt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startpunkt för order-service.
 */
@SpringBootApplication
public class OrderServiceProjekt2Application {

	/**
	 * Startar Spring Boot-applikationen.
	 *
	 * @param args kommandoradsargument till applikationen
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceProjekt2Application.class, args);
	}

}
