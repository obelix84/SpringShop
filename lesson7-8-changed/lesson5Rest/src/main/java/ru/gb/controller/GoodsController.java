package ru.gb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Good;
import ru.gb.repository.GoodsRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsRepository goodsRepository;

    public GoodsController(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @GetMapping
    public ResponseEntity<List<Good>>findAll(){
        List<Good> goods = new ArrayList<>();
        goodsRepository.findAll().forEach(goods::add);
        return ResponseEntity.ok(goods);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Good> findById(@PathVariable long id){
        Optional<Good> productOptional = goodsRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productOptional.get());
    }

    @PostMapping
    public ResponseEntity<Good> save(@RequestBody Good good) {
        Good newCreatedProduct = goodsRepository.save(good);
        return ResponseEntity.created(URI.create("/products/" + newCreatedProduct.getId())).body(newCreatedProduct);
    }

    @DeleteMapping("/{id}")
    public int deleteGood(@PathVariable Long id) {
        goodsRepository.deleteById(id);
         return HttpStatus.OK.value();
    }

}