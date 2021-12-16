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
    public ResponseEntity<Long> deleteById(@PathVariable("id") long id) {
        productService.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        //вот тут я не до конца понял как делать..
        //если кинуть, полный продукт, со всеми полями и всеми ссылками, то все норм
        //при это обновление можно кидать сюда же, он либо вставит новое, либо обновит.
         /*
         Вот такой например
         {
            "id": 1,
            "name": "Product_one",
            "category": [
                {
                    "id": 1,
                "name": "Category1"
                }
                ],
            "price": {
            "id": 1,
            "originalValue": 100.002
          },
            "discount": {
            "id": 1,
            "originalValue": 0.0
             }
            }
          */
        Product newCreatedProduct = productService.save(product);
        // а какие тут эксепшены обрабатывать?
        return ResponseEntity.created(URI.create("/products/" + newCreatedProduct.getId())).body(newCreatedProduct);
    }



}
