package se.iths.martin.orderserviceprojekt2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Orderrad i ordersvaret med namn, styckpris och antal.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
    private Long productId;
    private String name;
    private BigDecimal price;
    private int quantity;
}
