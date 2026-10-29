package se.iths.martin.orderserviceprojekt2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.martin.orderserviceprojekt2.dto.OrderRequestDTO;
import se.iths.martin.orderserviceprojekt2.dto.OrderResponseDTO;
import se.iths.martin.orderserviceprojekt2.service.OrderService;

/**
 * REST-controller för att skapa order åt den JWT-autentiserade användaren.
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Skapar en order med JWT:ns subject som kundnamn.
     *
     * @param request produkter och antal som kunden vill beställa
     * @param jwt verifierad JWT för den inloggade användaren
     * @return den skapade ordern med status {@code 201 Created}
     */
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderRequestDTO request,
            @AuthenticationPrincipal Jwt jwt) {

        String customerName = jwt.getSubject();
        String bearerToken = "Bearer " + jwt.getTokenValue();

        OrderResponseDTO response = orderService.createOrder(
                request,
                customerName,
                bearerToken
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
