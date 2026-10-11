package ru.rgrabelnikov.ordersapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.domain.OrderStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface OrderService {

    OrderEntity create(OrderEntity order);

    OrderEntity getById(UUID id);

    Page<OrderEntity> getAll(Pageable pageable);

    Long countOrdersByStatus(OrderStatus status);

    OffsetDateTime getLastProcessedAt();
}
