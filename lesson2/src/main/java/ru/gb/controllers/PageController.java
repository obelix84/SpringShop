package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.domain.User;
import ru.gb.repository.UserRepository;

@Controller
@RequestMapping("/")
public class PageController {

    private final UserRepository userRepository;

    public PageController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println(userRepository.toString());
    }

    @GetMapping("/adduser")
    public String userAddPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "adduser";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping
    public String findAll(Model model) {
        return "index";
    }

    @PostMapping("/adduser")
    public String add(User user) {
        userRepository.save(user);
        return "redirect:/";
    }

}