package ru.rgrabelnikov.ordersapi.dto;

import ru.rgrabelnikov.ordersapi.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record OrderRs(
        UUID id,
        OffsetDateTime createdAt,
        OffsetDateTime processedAt,
        OrderStatus status,
        BigDecimal amount,
        Integer itemsCount
) {
}
