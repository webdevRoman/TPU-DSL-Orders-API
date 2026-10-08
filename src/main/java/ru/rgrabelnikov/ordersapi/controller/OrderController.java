package ru.rgrabelnikov.ordersapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.dto.OrderCreateRq;
import ru.rgrabelnikov.ordersapi.dto.OrderCreatedEvent;
import ru.rgrabelnikov.ordersapi.dto.OrderRs;
import ru.rgrabelnikov.ordersapi.dto.PageRs;
import ru.rgrabelnikov.ordersapi.mapper.OrderMapper;
import ru.rgrabelnikov.ordersapi.service.EventSenderService;
import ru.rgrabelnikov.ordersapi.service.OrderService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final EventSenderService eventSenderService;

    private final OrderMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRs create(@Valid @RequestBody final OrderCreateRq request) {
        final OrderEntity order = mapper.toEntity(request);
        final OrderEntity savedOrder = orderService.create(order);

        final OrderCreatedEvent event = mapper.toCreatedEvent(savedOrder);
        eventSenderService.send(event);

        return mapper.toRs(savedOrder);
    }

    @GetMapping("/{id}")
    public OrderRs getById(@PathVariable final UUID id) {
        final OrderEntity order = orderService.getById(id);
        return mapper.toRs(order);
    }

    @GetMapping
    public PageRs<OrderRs> getAll(
            @PageableDefault(size = 20, sort = "createdAt,desc")
            @ParameterObject final Pageable pageable
    ) {
        final Page<OrderEntity> orders = orderService.getAll(pageable);
        return mapper.toPageRs(orders);
    }
}
