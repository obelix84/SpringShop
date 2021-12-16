package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable("id") long id) {
        productService.deleteById(id);
        return HttpStatus.ACCEPTED.value();
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product newCreatedProduct = productService.save(product);
        return ResponseEntity.created(URI.create("/products/" + newCreatedProduct.getId())).body(newCreatedProduct);
    }



}
