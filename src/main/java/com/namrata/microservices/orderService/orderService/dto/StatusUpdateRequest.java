package com.namrata.microservices.orderService.orderService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusUpdateRequest {

    @NotBlank
    private String status;
}
