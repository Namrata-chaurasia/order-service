package com.namrata.microservices.orderService.orderService.service;

import com.namrata.microservices.orderService.orderService.dto.CreateOrderRequest;
import com.namrata.microservices.orderService.orderService.entity.Order;
import com.namrata.microservices.orderService.orderService.exception.ResourceNotFoundException;
import com.namrata.microservices.orderService.orderService.repository.OrderRepository;
import com.namrata.microservices.orderService.orderService.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderServiceImpl service;

    @Test
    void shouldCreateOrder() {

        CreateOrderRequest request = new CreateOrderRequest();

        request.setProductId(1L);
        request.setQuantity(2);
        request.setCustomerName("Namrata");

        Order savedOrder = new Order();

        savedOrder.setId(1L);
        savedOrder.setProductId(1L);
        savedOrder.setQuantity(2);
        savedOrder.setCustomerName("Namrata");
        savedOrder.setStatus("CREATED");

        when(repository.save(any(Order.class)))
                .thenReturn(savedOrder);

        Order result = service.createOrder(request);

        assertNotNull(result);

        assertEquals(
                "CREATED",
                result.getStatus());
    }

    @Test
    void shouldGetOrderById() {

        Order order = new Order();
        order.setId(1L);
        order.setStatus("CREATED");

        when(repository.findById(1L))
                .thenReturn(Optional.of(order));

        Order result = service.getOrder(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldUpdateOrderStatus() {

        Order order = new Order();
        order.setId(1L);
        order.setStatus("CREATED");

        when(repository.findById(1L))
                .thenReturn(Optional.of(order));

        when(repository.save(any(Order.class)))
                .thenReturn(order);

        Order updated = service.updateStatus(1L, "DELIVERED");

        assertEquals("DELIVERED", updated.getStatus());

        verify(repository).save(order);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {

        when(repository.findById(100L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> service.getOrder(100L));

        assertTrue(
                exception.getMessage()
                        .contains("Order not found"));
    }
}