package se.iths.martin.orderserviceprojekt2.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.iths.martin.orderserviceprojekt2.dto.OrderResponseDTO;

/**
 * Publicerar orderuppgifter till RabbitMQ för vidare hantering av orderbekräftelser.
 */
@Component
@RequiredArgsConstructor
public class OrderMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.order-confirmation-queue}")
    private String orderConfirmationQueue;

    /**
     * Skickar ordern till den konfigurerade kön för orderbekräftelser.
     *
     * @param response orderuppgifter som ska skickas som meddelande
     * @throws org.springframework.amqp.AmqpException om meddelandet inte kan skickas
     */
    public void sendOrderConfirmation(OrderResponseDTO response) {
        rabbitTemplate.convertAndSend(orderConfirmationQueue, response);
    }
}
