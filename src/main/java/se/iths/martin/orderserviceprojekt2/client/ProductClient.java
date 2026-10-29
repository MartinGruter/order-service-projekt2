package se.iths.martin.orderserviceprojekt2.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import se.iths.martin.orderserviceprojekt2.dto.OrderRequestDTO;
import se.iths.martin.orderserviceprojekt2.dto.ProductInfoDTO;

import java.util.List;

/**
 * HTTP-klient för lagerminskning och hämtning av produktuppgifter från product-service.
 */
@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient productRestClient;

    /**
     * Begär lagerminskning för orderns produkter och vidarebefordrar användarens JWT.
     *
     * @param request orderns produkt-id:n och antal
     * @param bearerToken Authorization-header med prefixet {@code Bearer }
     * @return produktuppgifter med pris och beställt antal från product-service
     * @throws ResponseStatusException om product-service returnerar ett felsvar; statuskoden bevaras
     */
    public List<ProductInfoDTO> decreaseStock(
            OrderRequestDTO request,
            String bearerToken) {

        try {

            return productRestClient.post()
                    .uri("/products/stock/decrease")
                    .header("Authorization", bearerToken)
                    .body(request.getItems())
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

        } catch (RestClientResponseException exception) {

            throw new ResponseStatusException(
                    exception.getStatusCode(),
                    "Error from product-service: " +
                            exception.getResponseBodyAsString()
            );
        }
    }
}
