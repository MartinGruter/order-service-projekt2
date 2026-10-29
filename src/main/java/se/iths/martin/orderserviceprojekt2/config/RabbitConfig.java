package se.iths.martin.orderserviceprojekt2.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Konfigurerar RabbitMQ-kön och JSON-konvertering för orderbekräftelser.
 */
@Configuration
public class RabbitConfig {

    /**
     * Definierar en beständig kö för orderbekräftelser.
     *
     * @param queueName könamn från {@code rabbitmq.order-confirmation-queue}
     * @return kön som deklareras i RabbitMQ
     */
    @Bean
    public Queue queue(
            @Value("${rabbitmq.order-confirmation-queue}") String queueName
    ) {
        return new Queue(queueName, true);
    }

    /**
     * Skapar konverteraren som omvandlar meddelanden till och från JSON.
     *
     * @return JSON-konverterare för RabbitMQ-meddelanden
     */
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    /**
     * Skapar en RabbitTemplate med JSON-konvertering.
     *
     * @param connectionFactory anslutningsfabrik för RabbitMQ
     * @return mallen som används för att skicka meddelanden
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
