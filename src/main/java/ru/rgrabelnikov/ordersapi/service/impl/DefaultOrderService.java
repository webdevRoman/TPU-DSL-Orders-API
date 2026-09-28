package ru.rgrabelnikov.ordersapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.dto.OrderCreateRq;
import ru.rgrabelnikov.ordersapi.dto.OrderRs;
import ru.rgrabelnikov.ordersapi.dto.PageRs;
import ru.rgrabelnikov.ordersapi.exception.NotFoundException;
import ru.rgrabelnikov.ordersapi.mapper.OrderMapper;
import ru.rgrabelnikov.ordersapi.repo.OrderRepo;
import ru.rgrabelnikov.ordersapi.service.OrderService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepo repo;

    private final OrderMapper mapper;

    @Transactional
    public OrderRs create(final OrderCreateRq request) {
        final OrderEntity order = mapper.toEntity(request);
        return mapper.toRs(repo.save(order));
    }

    @Transactional(readOnly = true)
    public OrderRs getById(final UUID id) {
        return repo.findById(id)
                .map(mapper::toRs)
                .orElseThrow(() -> new NotFoundException(OrderEntity.class, id));
    }

    @Transactional(readOnly = true)
    public PageRs<OrderRs> getAll(final Pageable pageable) {
        return mapper.toPageRs(repo.findAll(pageable));
    }
}
