package ru.gb.mall.inventory.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.service.WarehouseItemService;
import java.util.Map;

@RestController
@RequestMapping("/warehouse")
public class WarehouseItemController {

    private final WarehouseItemService warehouseItemService;

    public WarehouseItemController(WarehouseItemService warehouseItemService) {
        this.warehouseItemService = warehouseItemService;
    }

    @GetMapping("/amount/{id}")
    public ResponseEntity<Long> amountById(@PathVariable("id") long id) {
        return ResponseEntity.ok(warehouseItemService.getAvailableAmountOfProduct(id));
    }

    //на вход прилетает Map содержащая список того, что надо отгрузить
    //"product_id": amount
    @PostMapping
    public ResponseEntity<Long> shipmentByList(@RequestBody Map<Long, Long> shipmentList) {
        long totalAmount = 0;
        for (Map.Entry<Long, Long> entry : shipmentList.entrySet()) {
            warehouseItemService.shipment(entry.getKey(), entry.getValue());
            totalAmount += entry.getValue();
        }
        return ResponseEntity.ok(totalAmount);
    }

}