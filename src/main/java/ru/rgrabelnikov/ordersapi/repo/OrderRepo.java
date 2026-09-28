package ru.rgrabelnikov.ordersapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rgrabelnikov.ordersapi.domain.OrderEntity;

import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, UUID> {
}
