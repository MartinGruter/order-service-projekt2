package se.iths.martin.orderserviceprojekt2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Inkommande order med produkt-id:n och önskat antal för varje orderrad.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private List<OrderItemRequestDTO> items;
}
