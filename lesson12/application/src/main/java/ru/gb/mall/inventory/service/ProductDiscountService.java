package ru.gb.mall.inventory.service;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.ProductDiscount;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductDiscountRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;


@Service
public class ProductDiscountService {

    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountService(ProductDiscountRepository productDiscountRepository) {
        this.productDiscountRepository = productDiscountRepository;
    }

    public List<ProductDiscount> findAll() {
        return StreamSupport.stream(productDiscountRepository.findAll().spliterator(), true).toList();
    }

    public ProductDiscount findById(long id) {
        try {
            return productDiscountRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public void deleteById(long id) {
        try {
            productDiscountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public ProductDiscount save(ProductDiscount productDiscount) {
        return productDiscountRepository.save(productDiscount);
    }
}