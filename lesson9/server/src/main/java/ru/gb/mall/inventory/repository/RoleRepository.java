package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mall.inventory.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}