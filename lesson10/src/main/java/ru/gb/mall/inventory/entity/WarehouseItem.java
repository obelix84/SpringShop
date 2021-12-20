package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table (name = "WAREHOUSE_ITEM")
public class WarehouseItem implements Serializable {
    // чуть упрощаем
    // считаем, что на товар может быть только на одном складе
    @EmbeddedId
    private WarehouseItemId warehouseItemId;
    private long amount;
}
