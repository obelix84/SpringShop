package ru.gb.mall.inventory.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.WarehouseItemRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class WarehouseItemService {

    private final WarehouseItemRepository warehouseItemRepository;

    public WarehouseItemService(WarehouseItemRepository warehouseItemRepository) {
        this.warehouseItemRepository = warehouseItemRepository;
    }

    public List<WarehouseItem> findAll() {
        return StreamSupport.stream(warehouseItemRepository.findAll().spliterator(), true).toList();
    }

//    public WarehouseItem findById(long id) {
//        try {
//            return warehouseItemRepository.findById(id).orElseThrow();
//        } catch (NoSuchElementException e) {
//            throw new EntityNotFoundException("Warehouse Item entity no found by id: " + id, e);
//        }
//    }
//
//    public void deleteById(long id) {
//        try {
//            warehouseItemRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new EntityNotFoundException("Warehouse Item entity no found by id: " + id, e);
//        }
//    }

    public WarehouseItem save(WarehouseItem warehouseItem) {
        return warehouseItemRepository.save(warehouseItem);
    }


    public long shipment(long productId, long amount) {
       // return warehouseItemRepository.getAmountByProductId(productId);
        return 10;
    }
}
