package dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private String customerName;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDTO> items;
}
