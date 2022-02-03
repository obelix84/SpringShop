package ru.gb.mall.inventory.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseItemId;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WarehouseItemRepository extends CrudRepository<WarehouseItem, WarehouseItemId> {

    @Query("SELECT w.amount FROM WarehouseItem w WHERE w.warehouseItemId.product.id = ?1")
    long getAmountByProductId(long productId);

    @Query("SELECT w FROM WarehouseItem w WHERE w.warehouseItemId.product.id = ?1")
    WarehouseItem findByProductId(long productId);

    @Modifying
    @Query("update WarehouseItem w set w.amount = :amount where w.warehouseItemId.product.id = :productId")
    int updateWarehouseItemSetAmountByProductId(@Param("productId") long productId, @Param("amount") long amount);
}