package ru.rgrabelnikov.ordersapi.dto;

import java.util.List;

public record PageRs<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
}
