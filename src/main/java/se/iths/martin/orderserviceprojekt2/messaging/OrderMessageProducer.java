package se.iths.martin.orderserviceprojekt2.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.iths.martin.orderserviceprojekt2.dto.OrderResponseDTO;

@Component
@RequiredArgsConstructor
public class OrderMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.order-confirmation-queue}")
    private String orderConfirmationQueue;

    public void sendOrderConfirmation(OrderResponseDTO response) {
        rabbitTemplate.convertAndSend("new-queue", response);
    }
}
