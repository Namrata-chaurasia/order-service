package com.namrata.microservices.orderService.orderService.controller;

import com.namrata.microservices.orderService.orderService.dto.CreateOrderRequest;
import com.namrata.microservices.orderService.orderService.dto.StatusUpdateRequest;
import com.namrata.microservices.orderService.orderService.entity.Order;
import com.namrata.microservices.orderService.orderService.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Valid
            @RequestBody CreateOrderRequest request) {

        return ResponseEntity.ok(
                orderService.createOrder(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {

        return ResponseEntity.ok(
                orderService.updateStatus(
                        id,
                        request.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrder(id));
    }
}
