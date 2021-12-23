package ru.gb.mall.inventory.service;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.ProductPrice;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductPriceRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;


@Service
public class ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    public ProductPriceService(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    public List<ProductPrice> findAll() {
        return StreamSupport.stream(productPriceRepository.findAll().spliterator(), true).toList();
    }

    public ProductPrice findById(long id) {
        try {
            return productPriceRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public void deleteById(long id) {
        try {
            productPriceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public ProductPrice save(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }
}