package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mall.inventory.entity.Warehouse;

@Repository
public interface WarehouseRepository extends CrudRepository<Long, Warehouse> {
}