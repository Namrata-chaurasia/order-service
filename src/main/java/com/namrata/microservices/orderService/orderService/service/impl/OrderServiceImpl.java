package com.namrata.microservices.orderService.orderService.service.impl;

import com.namrata.microservices.orderService.orderService.dto.CreateOrderRequest;
import com.namrata.microservices.orderService.orderService.entity.Order;
import com.namrata.microservices.orderService.orderService.exception.ResourceNotFoundException;
import com.namrata.microservices.orderService.orderService.repository.OrderRepository;
import com.namrata.microservices.orderService.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl
        implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .customerName(request.getCustomerName())
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(order);
    }

    @Override
    public Order updateStatus(Long id,
                              String status) {

        Order order = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with id: " + id));

        order.setStatus(status);

        return repository.save(order);
    }

    @Override
    public Order getOrder(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id));
    }
}
