package controller;

import dto.OrderRequestDTO;
import dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

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