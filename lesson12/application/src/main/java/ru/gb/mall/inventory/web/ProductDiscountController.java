package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.ProductDiscount;
import ru.gb.mall.inventory.service.ProductDiscountService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products/discounts")
public class ProductDiscountController {

    private final ProductDiscountService productDiscountService;

    public ProductDiscountController(ProductDiscountService productDiscountService) {
        this.productDiscountService = productDiscountService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDiscount>> findAll() {
        return ResponseEntity.ok(productDiscountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDiscount> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productDiscountService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") long id) {
        productDiscountService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<ProductDiscount> save(@RequestBody ProductDiscount productDiscount) {
        ProductDiscount newCreatedProductDiscount = productDiscountService.save(productDiscount);
        return ResponseEntity.created(URI.create("/products/" + newCreatedProductDiscount.getId())).body(newCreatedProductDiscount);
    }
}

