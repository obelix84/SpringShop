package ru.gb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.entity.Good;
import ru.gb.repository.GoodsRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsRepository goodRepo;

    public GoodsController(GoodsRepository goodRepo) {
        this.goodRepo = goodRepo;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Good> goods = new ArrayList<>();
        goodRepo.findAll().forEach(goods::add);
        model.addAttribute("goods", goods);
        return "goods-all";
    }

    @GetMapping("/add")
    public String addForm(Good good) {
        return "goods-add";
    }

    @PostMapping("/add")
    public String add(Good good) {
        goodRepo.save(good);
        return "redirect:/goods";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        goodRepo.deleteById(id);
        return "redirect:/goods";
    }

}