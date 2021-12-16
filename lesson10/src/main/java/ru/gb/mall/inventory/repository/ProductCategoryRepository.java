package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.Product;

public interface ProductCategoryRepository extends PagingAndSortingRepository<Product, Long> {
}
