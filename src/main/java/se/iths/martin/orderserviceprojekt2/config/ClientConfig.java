package se.iths.martin.orderserviceprojekt2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Konfigurerar HTTP-klienten som används för anrop till product-service.
 */
@Configuration
public class ClientConfig {

    /**
     * Skapar en RestClient med product-services basadress.
     *
     * @param baseUrl basadress från {@code product-service-base-url}
     * @return klient för produktanrop
     */
    @Bean
    public RestClient productRestClient(@Value("${product-service-base-url}") String baseUrl) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
