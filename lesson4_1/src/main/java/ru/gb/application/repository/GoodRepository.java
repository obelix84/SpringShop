package ru.gb.application.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.application.entity.Good;


import java.math.BigInteger;

@Repository
public interface GoodRepository extends CrudRepository<Good, Long> {

}