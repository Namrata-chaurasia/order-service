package com.namrata.microservices.orderService.orderService.service;

import com.namrata.microservices.orderService.orderService.dto.CreateOrderRequest;
import com.namrata.microservices.orderService.orderService.entity.Order;

public interface OrderService {

    Order createOrder(CreateOrderRequest request);

    Order updateStatus(Long id, String status);

    Order getOrder(Long id);
}
