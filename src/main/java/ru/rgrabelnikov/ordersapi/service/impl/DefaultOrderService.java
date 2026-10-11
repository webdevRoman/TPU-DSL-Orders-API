package ru.rgrabelnikov.ordersapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.domain.OrderStatus;
import ru.rgrabelnikov.ordersapi.exception.NotFoundException;
import ru.rgrabelnikov.ordersapi.repo.OrderRepo;
import ru.rgrabelnikov.ordersapi.service.OrderService;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepo repo;

    @Transactional
    public OrderEntity create(final OrderEntity order) {
        final OrderEntity savedOrder = repo.save(order);
        log.info("Created order {}", savedOrder.getId());
        return savedOrder;
    }

    @Transactional(readOnly = true)
    public OrderEntity getById(final UUID id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException(OrderEntity.class, id));
    }

    @Transactional(readOnly = true)
    public Page<OrderEntity> getAll(final Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Long countOrdersByStatus(final OrderStatus status) {
        return repo.countByStatus(status);
    }

    @Override
    public OffsetDateTime getLastProcessedAt() {
        return repo.findLastProcessedAt().orElse(null);
    }
}
