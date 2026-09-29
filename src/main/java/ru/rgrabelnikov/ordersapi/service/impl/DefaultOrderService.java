package ru.rgrabelnikov.ordersapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.exception.NotFoundException;
import ru.rgrabelnikov.ordersapi.repo.OrderRepo;
import ru.rgrabelnikov.ordersapi.service.OrderService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepo repo;

    @Transactional
    public OrderEntity create(final OrderEntity order) {
        return repo.save(order);
    }

    @Transactional(readOnly = true)
    public OrderEntity getById(final UUID id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException(OrderEntity.class, id));
    }

    @Transactional(readOnly = true)
    public Page<OrderEntity> getAll(final Pageable pageable) {
        return repo.findAll(pageable);
    }
}
