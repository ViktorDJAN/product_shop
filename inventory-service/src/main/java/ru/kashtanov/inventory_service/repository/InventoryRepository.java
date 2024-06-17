package ru.kashtanov.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.inventory_service.model.Inventory;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Query("SELECT i FROM Inventory i WHERE i.skuCode = ?1")
    List<Inventory> findBySkuCode(String skuCode);

    @Query("SELECT i FROM Inventory i WHERE i.skuCode = ?1")
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
