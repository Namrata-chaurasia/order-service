package com.namrata.microservices.orderService.orderService.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateOrderResponse {

    private Long id;

    private Long productId;

    private Integer quantity;

    private String customerName;

    private String status;

    private LocalDateTime createdAt;
}
