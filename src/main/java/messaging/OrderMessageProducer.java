package messaging;

import dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.order-confirmation-queue}")
    private String orderConfirmationQueue;

    public void sendOrderConfirmation(OrderResponseDTO response) {
        rabbitTemplate.convertAndSend(orderConfirmationQueue, response);
    }
}
