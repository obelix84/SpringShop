package ru.gb.mall.inventory.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}