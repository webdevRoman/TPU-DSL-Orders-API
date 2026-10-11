package ru.rgrabelnikov.ordersapi.dto;

import java.util.UUID;

public record OrderCreatedEvent(
        UUID id
) {
}
