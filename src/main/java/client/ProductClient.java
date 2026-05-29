package client;

import dto.OrderRequestDTO;
import dto.ProductInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient productRestClient;

    public List<ProductInfoDTO> decreaseStock(
            OrderRequestDTO request,
            String bearerToken) {

        try {

            return productRestClient.post()
                    .uri("/products/stock/decrease")
                    .header("Authorization", bearerToken)
                    .body(request)
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