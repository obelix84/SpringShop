package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WAREHOUSE_KEEPER")
public class WarehouseKeeper {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

}
