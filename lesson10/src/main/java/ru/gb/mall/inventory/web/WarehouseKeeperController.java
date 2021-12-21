package ru.gb.mall.inventory.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.service.WarehouseKeeperService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/warehouse/keepers")
public class WarehouseKeeperController {

    private final WarehouseKeeperService warehouseKeeperService;

    public WarehouseKeeperController(WarehouseKeeperService warehouseKeeperService) {
        this.warehouseKeeperService = warehouseKeeperService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseKeeper>> findAll() {
        return ResponseEntity.ok(warehouseKeeperService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseKeeper> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(warehouseKeeperService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") long id) {
        warehouseKeeperService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<WarehouseKeeper> save(@RequestBody WarehouseKeeper warehouseKeeper) {
        WarehouseKeeper newCreatedKeeper = warehouseKeeperService.save(warehouseKeeper);
        return ResponseEntity.created(URI.create("/warehouse/keepers/" + newCreatedKeeper.getId())).body(newCreatedKeeper);
    }



}
