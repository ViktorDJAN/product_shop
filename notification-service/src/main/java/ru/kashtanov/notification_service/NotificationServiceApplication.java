package ru.kashtanov.notification_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import ru.kashtanov.notification_service.event.OrderPlacedEvent;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notification_topic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		// send out an email notification
		log.info("Received Notification from Order - {}" ,orderPlacedEvent.getOrderNumber());

	}


}
