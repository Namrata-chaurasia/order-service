package com.namrata.microservices.orderService.orderService.repository;

import com.namrata.microservices.orderService.orderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long> {
}
