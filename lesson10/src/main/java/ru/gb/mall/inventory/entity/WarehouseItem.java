package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table (name = "WAREHOUSE_ITEM")
public class WarehouseItem implements Serializable {
    //warehouseId, productId, amount
    @Id
    @OneToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @Id
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private long amount;
}
