package se.iths.martin.orderserviceprojekt2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Orderuppgifter som returneras till klienten och skickas som orderbekräftelsemeddelande.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String customerName;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDTO> items;
}
