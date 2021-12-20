package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseItemId;

@Repository
public interface WarehouseItemRepository extends CrudRepository<WarehouseItem, WarehouseItemId> {
    //long getAmountByProductId(long productId);
}