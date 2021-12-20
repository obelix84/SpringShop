package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "WAREHOUSE")
@Entity
@Data
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "ADDRESS")
    private String address;

    //считаем, что у одного склада может быть один кипер, но при этом кипер
    //может администрировать несколько складов, т.е. M:1
    // т.е. у склада есть один кипер у кипера несколько складов
    @ManyToOne
    @JoinColumn(
            name = "warehouse_keeper_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_WAREHOUSE_KEEPER_WAREHOUSE_ID_RELATION")
    )
    private WarehouseKeeper warehouseKeeper;
}
