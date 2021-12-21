package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.mail.EmailServiceImpl;
import ru.gb.mall.inventory.mail.message.EmailMessage;
import ru.gb.mall.inventory.repository.ProductRepository;
import ru.gb.mall.inventory.repository.WarehouseItemRepository;


import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class WarehouseItemService {

    private final WarehouseItemRepository warehouseItemRepository;
    private final EmailServiceImpl emailService;

    public WarehouseItemService(WarehouseItemRepository warehouseItemRepository, EmailServiceImpl emailService) {
        this.warehouseItemRepository = warehouseItemRepository;
        this.emailService = emailService;
    }

    public List<WarehouseItem> findAll() {
        return StreamSupport.stream(warehouseItemRepository.findAll().spliterator(), true).toList();
    }

    public WarehouseItem save(WarehouseItem warehouseItem) {
        return warehouseItemRepository.save(warehouseItem);
    }

    //нужно для формированирования корректной Map при заказе
    public long getAvailableAmountOfProduct(long productId) {
        long amount;
        try {
            amount = warehouseItemRepository.getAmountByProductId(productId);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Where is no this product at warehouse!");
        }
        return amount;
    }

    public long shipment(long productId, long amount) {
        long amountAtWH, shippedAmount;
        amountAtWH = this.getAvailableAmountOfProduct(productId);
        if (amountAtWH < amount) {
            shippedAmount = amountAtWH;
            warehouseItemRepository.updateWarehouseItemSetAmountByProductId(productId, 0L);
        } else {
            shippedAmount = amount;
            warehouseItemRepository.updateWarehouseItemSetAmountByProductId(productId, amountAtWH - amount);
        }
        if (amountAtWH - amount <= 10) {
            WarehouseItem warehouseItem = warehouseItemRepository.findByProductId(productId);
            WarehouseKeeper keeper = warehouseItem.getWarehouseItemId().getWarehouse().getWarehouseKeeper();
            EmailMessage message = new EmailMessage("sonicx921@gmail.com", keeper.getEmail(),
                    "Product " + warehouseItem.getWarehouseItemId().getProduct().getName() + " amount is low",
                    "Dear " + keeper.getName() + ", \n Bla bla bla...");
            emailService.send(message);
        }
        WarehouseItem warehouseItem = warehouseItemRepository.findByProductId(productId);
        WarehouseKeeper keeper = warehouseItem.getWarehouseItemId().getWarehouse().getWarehouseKeeper();
        EmailMessage message = new EmailMessage("sonicx921@gmail.com", keeper.getEmail(),
                "Product " + warehouseItem.getWarehouseItemId().getProduct().getName() + " has been shipped",
                "Dear,  \n Bla bla bla...");
        emailService.send(message);
        return shippedAmount;
    }
}
