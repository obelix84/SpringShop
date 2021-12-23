package ru.gb.mall.inventory.service;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.ProductCategory;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductCategoryRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;


@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> findAll() {
        return StreamSupport.stream(productCategoryRepository.findAll().spliterator(), true).toList();
    }

    public ProductCategory findById(long id) {
        try {
            return productCategoryRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public void deleteById(long id) {
        try {
            productCategoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}