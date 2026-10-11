package ru.rgrabelnikov.ordersapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;
import ru.rgrabelnikov.ordersapi.domain.OrderStatus;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, UUID> {

    Long countByStatus(OrderStatus status);

    @Query("""
                select o.processedAt
                from OrderEntity o
                order by o.processedAt desc nulls last
                limit 1
            """)
    Optional<OffsetDateTime> findLastProcessedAt();
}
