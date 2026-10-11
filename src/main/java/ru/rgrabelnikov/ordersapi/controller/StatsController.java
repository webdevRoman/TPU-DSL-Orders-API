package ru.rgrabelnikov.ordersapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rgrabelnikov.ordersapi.domain.OrderStatus;
import ru.rgrabelnikov.ordersapi.dto.OrderStatsRs;
import ru.rgrabelnikov.ordersapi.mapper.OrderMapper;
import ru.rgrabelnikov.ordersapi.service.OrderService;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/stats")
public class StatsController {

    private final OrderService orderService;

    private final OrderMapper mapper;

    @GetMapping
    public OrderStatsRs get() {
        log.info("Getting orders internal stats");
        final Long newCount = orderService.countOrdersByStatus(OrderStatus.NEW);
        final Long processingCount = orderService.countOrdersByStatus(OrderStatus.PROCESSING);
        final Long doneCount = orderService.countOrdersByStatus(OrderStatus.DONE);
        final OffsetDateTime lastProcessedAt = orderService.getLastProcessedAt();
        return mapper.toStatsRs(newCount, processingCount, doneCount, lastProcessedAt);
    }
}
