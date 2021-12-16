package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.Product;

public interface ProductPriceRepository extends PagingAndSortingRepository<Product, Long> {
}
