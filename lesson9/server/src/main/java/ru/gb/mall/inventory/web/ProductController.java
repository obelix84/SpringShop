package ru.gb.mall.inventory.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.Role;
import ru.gb.mall.inventory.entity.User;
import ru.gb.mall.inventory.repository.RoleRepository;
import ru.gb.mall.inventory.repository.UserRepository;
import ru.gb.mall.inventory.service.ProductService;
import ru.gb.mall.inventory.service.UserService;

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
}

