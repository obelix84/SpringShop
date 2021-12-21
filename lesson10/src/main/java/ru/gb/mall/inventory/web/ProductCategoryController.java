package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.ProductCategory;
import ru.gb.mall.inventory.service.ProductCategoryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> findAll() {
        return ResponseEntity.ok(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") long id) {
        productCategoryService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> save(@RequestBody ProductCategory productCategory) {
        ProductCategory newCreatedProductCategory = productCategoryService.save(productCategory);
        return ResponseEntity.created(URI.create("/products/" + newCreatedProductCategory.getId())).body(newCreatedProductCategory);
    }
}