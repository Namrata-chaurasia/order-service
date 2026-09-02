package com.namrata.microservices.orderService.orderService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namrata.microservices.orderService.orderService.dto.CreateOrderRequest;
import com.namrata.microservices.orderService.orderService.dto.StatusUpdateRequest;
import com.namrata.microservices.orderService.orderService.entity.Order;
import com.namrata.microservices.orderService.orderService.service.OrderService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateOrder() throws Exception {

        CreateOrderRequest request = new CreateOrderRequest();
        request.setProductId(1L);
        request.setQuantity(2);
        request.setCustomerName("Namrata");

        Order order = new Order();
        order.setId(1L);
        order.setProductId(1L);
        order.setQuantity(2);
        order.setCustomerName("Namrata");
        order.setStatus("CREATED");

        when(service.createOrder(any(CreateOrderRequest.class)))
                .thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.customerName")
                        .value("Namrata"))
                .andExpect(jsonPath("$.status")
                        .value("CREATED"));

        verify(service, times(1))
                .createOrder(any(CreateOrderRequest.class));
    }

    @Test
    void shouldGetOrderById() throws Exception {

        Order order = new Order();
        order.setId(1L);
        order.setStatus("CREATED");

        when(service.getOrder(1L))
                .thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1))
                .andExpect(jsonPath("$.status")
                        .value("CREATED"));
    }

    @Test
    void shouldUpdateOrderStatus() throws Exception {

        StatusUpdateRequest request =
                new StatusUpdateRequest();

        request.setStatus("DELIVERED");

        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        updatedOrder.setProductId(1L);
        updatedOrder.setQuantity(2);
        updatedOrder.setCustomerName("Namrata");
        updatedOrder.setStatus("DELIVERED");

        when(service.updateStatus(1L, "DELIVERED"))
                .thenReturn(updatedOrder);

        mockMvc.perform(put("/orders/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status")
                        .value("DELIVERED"));

        verify(service, times(1))
                .updateStatus(1L, "DELIVERED");
    }
}
