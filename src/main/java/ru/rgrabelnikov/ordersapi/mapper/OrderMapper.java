package ru.rgrabelnikov.ordersapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.dto.OrderCreateRq;
import ru.rgrabelnikov.ordersapi.dto.OrderCreatedEvent;
import ru.rgrabelnikov.ordersapi.dto.OrderRs;
import ru.rgrabelnikov.ordersapi.dto.PageRs;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "processedAt", ignore = true)
    @Mapping(target = "status", constant = "NEW")
    OrderEntity toEntity(OrderCreateRq rq);

    OrderRs toRs(OrderEntity order);

    default PageRs<OrderRs> toPageRs(final Page<OrderEntity> page) {
        final List<OrderRs> content = page.map(this::toRs).getContent();
        return new PageRs<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    OrderCreatedEvent toCreatedEvent(OrderEntity order);
}
