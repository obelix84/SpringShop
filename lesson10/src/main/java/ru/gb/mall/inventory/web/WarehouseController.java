package ru.gb.mall.inventory.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.service.WarehouseService;

import java.net.URI;
import java.util.List;

public class WarehouseController {
    
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") long id) {
        warehouseService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<Warehouse> save(@RequestBody Warehouse warehouse) {
        Warehouse newCreated = warehouseService.save(warehouse);
        return ResponseEntity.created(URI.create("/warehouse/" + newCreated.getId())).body(newCreated);
    }
}
