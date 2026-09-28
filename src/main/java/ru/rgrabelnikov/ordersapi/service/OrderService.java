package ru.rgrabelnikov.ordersapi.service;

import org.springframework.data.domain.Pageable;
import ru.rgrabelnikov.ordersapi.dto.OrderCreateRq;
import ru.rgrabelnikov.ordersapi.dto.OrderRs;
import ru.rgrabelnikov.ordersapi.dto.PageRs;

import java.util.UUID;

public interface OrderService {

    OrderRs create(OrderCreateRq request);

    OrderRs getById(UUID id);

    PageRs<OrderRs> getAll(Pageable pageable);
}
