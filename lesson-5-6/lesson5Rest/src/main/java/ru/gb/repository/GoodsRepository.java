package ru.gb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entity.Good;

@Repository
public interface  GoodsRepository extends CrudRepository<Good, Long> {

}