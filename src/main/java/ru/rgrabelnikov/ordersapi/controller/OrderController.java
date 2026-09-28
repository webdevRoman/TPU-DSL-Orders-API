package ru.rgrabelnikov.ordersapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import ru.rgrabelnikov.ordersapi.dto.OrderCreateRq;
import ru.rgrabelnikov.ordersapi.dto.OrderRs;
import ru.rgrabelnikov.ordersapi.dto.PageRs;
import ru.rgrabelnikov.ordersapi.service.OrderService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRs create(@Valid @RequestBody final OrderCreateRq request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public OrderRs getById(@PathVariable final UUID id) {
        return service.getById(id);
    }

    @GetMapping
    public PageRs<OrderRs> getAll(
            @PageableDefault(size = 20, sort = "createdAt,desc") final Pageable pageable
    ) {
        return service.getAll(pageable);
    }
}
