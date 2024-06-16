package ru.kashtanov.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kashtanov.order_service.model.AnOrder;

@Repository
public interface AnOrderRepository extends JpaRepository<AnOrder,Long> {
}
