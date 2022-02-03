package ru.gb.mall.inventory.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.WarehouseKeeperRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class WarehouseKeeperService {

    private final WarehouseKeeperRepository warehouseKeeperRepository;

    public WarehouseKeeperService(WarehouseKeeperRepository warehouseKeeperRepository) {
        this.warehouseKeeperRepository = warehouseKeeperRepository;
    }

    public List<WarehouseKeeper> findAll() {
        return StreamSupport.stream(warehouseKeeperRepository.findAll().spliterator(), true).toList();
    }

    public WarehouseKeeper findById(long id) {
        try {
            return warehouseKeeperRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Warehouse keeper entity no found by id: " + id, e);
        }
    }

    public void deleteById(long id) {
        try {
            warehouseKeeperRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Warehouse keeper entity no found by id: " + id, e);
        }
    }

    public WarehouseKeeper save(WarehouseKeeper warehouseKeeper) {
        return warehouseKeeperRepository.save(warehouseKeeper);
    }
}
