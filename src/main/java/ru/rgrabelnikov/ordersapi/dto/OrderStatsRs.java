package ru.rgrabelnikov.ordersapi.dto;

import java.time.OffsetDateTime;

public record OrderStatsRs(
        Long ordersNew,
        Long ordersProcessing,
        Long ordersDone,
        OffsetDateTime lastProcessedAt
) {
}
