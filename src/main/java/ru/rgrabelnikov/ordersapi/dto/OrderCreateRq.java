package ru.rgrabelnikov.ordersapi.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderCreateRq(

        @NotNull
        @Positive
        @Digits(integer = 8, fraction = 2)
        BigDecimal amount,

        @NotNull
        @Positive
        Integer itemsCount
) {
}
