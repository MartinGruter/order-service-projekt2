package se.iths.martin.orderserviceprojekt2.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.martin.orderserviceprojekt2.Repository.OrderRepository;
import se.iths.martin.orderserviceprojekt2.client.ProductClient;
import se.iths.martin.orderserviceprojekt2.dto.OrderItemResponseDTO;
import se.iths.martin.orderserviceprojekt2.dto.OrderRequestDTO;
import se.iths.martin.orderserviceprojekt2.dto.OrderResponseDTO;
import se.iths.martin.orderserviceprojekt2.dto.ProductInfoDTO;
import se.iths.martin.orderserviceprojekt2.messaging.OrderMessageProducer;
import se.iths.martin.orderserviceprojekt2.model.Order;
import se.iths.martin.orderserviceprojekt2.model.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderMessageProducer orderMessageProducer;

    public OrderResponseDTO createOrder(
            OrderRequestDTO request,
            String customerName,
            String bearerToken) {

        List<ProductInfoDTO> products =
                productClient.decreaseStock(request, bearerToken);

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerName(customerName);

        List<OrderItem> orderItems = new ArrayList<>();

        for (ProductInfoDTO product : products) {

            OrderItem item = new OrderItem();
            item.setName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(product.getQuantity());
            item.setOrder(order);

            orderItems.add(item);
        }

        BigDecimal totalPrice = products.stream()
                .map(product ->
                        product.getPrice()
                                .multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);

        OrderResponseDTO response = mapToResponse(savedOrder);

        orderMessageProducer.sendOrderConfirmation(response);

        return response;
    }

    private OrderResponseDTO mapToResponse(Order order) {

        List<OrderItemResponseDTO> items =
                order.getOrderItems()
                        .stream()
                        .map(item -> new OrderItemResponseDTO(
                                item.getId(),
                                item.getName(),
                                item.getPrice(),
                                item.getQuantity()
                        ))
                        .toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getOrderDate(),
                order.getCustomerName(),
                order.getTotalPrice(),
                items
        );
    }
}