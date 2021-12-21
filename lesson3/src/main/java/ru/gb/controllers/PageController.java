package ru.gb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.domain.User;
import ru.gb.repository.UserRepository;

import java.security.Principal;
import java.util.logging.SocketHandler;

@Controller
@RequestMapping("/")
public class PageController {

    private final UserRepository userRepository;

    public PageController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

//    @GetMapping("/logout")
//    public String logoutPage(Model model) {
//        return "redirect:/";
//    }

    @GetMapping
    public String indexPage(Model model, Principal principal) {
        if (principal != null) {
            System.out.println(principal);
            model.addAttribute("principal", principal);
        }
        return "index";
    }

    @PostMapping("/adduser")
    public String add(User user) {
        userRepository.save(user);
        return "redirect:/";
    }

}