package service;

import Repository.OrderRepository;
import jakarta.transaction.Transactional;
import model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder() {


        return null;
    }
}
