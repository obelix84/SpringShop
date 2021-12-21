package ru.gb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.domain.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
