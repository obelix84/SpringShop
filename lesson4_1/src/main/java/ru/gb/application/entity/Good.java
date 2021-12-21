package ru.gb.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    public Good() {
    }

}