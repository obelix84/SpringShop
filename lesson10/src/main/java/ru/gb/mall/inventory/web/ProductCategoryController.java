package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.ProductPrice;
import ru.gb.mall.inventory.service.ProductPriceService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products/prices")
public class ProductCategoryController {

    private final ProductPriceService productPriceService;

    public ProductCategoryController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @GetMapping
    public ResponseEntity<List<ProductPrice>> findAll() {
        return ResponseEntity.ok(productPriceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductPrice> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productPriceService.findById(id));
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable("id") long id) {
        productPriceService.deleteById(id);
        return HttpStatus.ACCEPTED.value();
    }

    @PostMapping
    public ResponseEntity<ProductPrice> save(@RequestBody ProductPrice productPrice) {
        ProductPrice newCreatedProductPrice = productPriceService.save(productPrice);
        return ResponseEntity.created(URI.create("/products/" + newCreatedProductPrice.getId())).body(newCreatedProductPrice);
    }
}